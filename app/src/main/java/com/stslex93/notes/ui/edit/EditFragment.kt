package com.stslex93.notes.ui.edit

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex93.notes.R
import com.stslex93.notes.core.Resource
import com.stslex93.notes.databinding.FragmentEditBinding
import com.stslex93.notes.ui.model.NoteUI
import com.stslex93.notes.utilites.BaseFragment
import com.stslex93.notes.utilites.hideKeyboard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalCoroutinesApi
class EditFragment : BaseFragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val args: EditFragmentArgs by navArgs()

    private lateinit var editTitle: String
    private lateinit var editContent: String

    private val viewModel: EditNoteViewModel by viewModels { viewModelFactory.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.transition_duration).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        requireActivity().hideKeyboard()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editCardView.transitionName = args.transitionName
        if (args.edit) getNoteJob.start()
        else setCurrentTime()
        binding.editFragmentReturn.setOnClickListener { findNavController().popBackStack() }
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentTime() {
        val timeFormat = getString(R.string.time_format)
        val currentTimeInMs = System.currentTimeMillis()
        val locale = Locale.getDefault()
        val currentTime = SimpleDateFormat(timeFormat, locale).format(currentTimeInMs)
        val labelEdit = getString(R.string.label_edit)
        binding.editTime.text = "$labelEdit: $currentTime"
    }

    private val getNoteJob by lazy {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getNoteById(args.id).collect(::collector)
        }
    }

    private suspend fun collector(resource: Resource<NoteUI>) {
        when (resource) {
            is Resource.Success -> resource.result()
            is Resource.Failure -> {}
            is Resource.Loading -> {}
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun Resource.Success<NoteUI>.result() = withContext(Dispatchers.Main) {
        with(binding) {
            data.bindEditNote(editInputTitle, editInputContent)
            data.setLastEditTime(editTime, getString(R.string.label_edit))
        }
    }

    override fun onStop() {
        super.onStop()
        saveData()
        binding.editFragmentReturn.hide()
    }

    override fun onResume() {
        super.onResume()
        binding.editFragmentReturn.apply { if (isGone) show() }
    }

    private fun saveData() {
        val title = binding.editInputTitle.editText?.text.toString()
        val content = binding.editInputContent.editText?.text.toString()
        val dateFormat = getString(R.string.date_format)
        val timeFormat = getString(R.string.time_format)
        val locale = Locale.getDefault()
        val datestamp = SimpleDateFormat(dateFormat, locale).format(System.currentTimeMillis())
        val timestamp = SimpleDateFormat(timeFormat, locale).format(System.currentTimeMillis())
        val note = NoteUI.Base(
            id = args.id,
            title = title,
            content = content,
            datestamp = datestamp,
            timestamp = timestamp
        )
        if (args.edit) {
            viewModel.updateNote(note)
        } else if (!args.edit && (title.isNotEmpty() || content.isNotEmpty())) {
            viewModel.insertNote(note)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        getNoteJob.cancel()
    }
}