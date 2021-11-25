package com.stslex93.notes.ui.edit

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex93.notes.R
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.databinding.FragmentEditBinding
import com.stslex93.notes.ui.NoteViewModel
import com.stslex93.notes.utilites.BaseFragment
import com.stslex93.notes.utilites.hideKeyboard
import com.stslex93.notes.utilites.observeOnce
import java.text.SimpleDateFormat

class EditFragment : BaseFragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private var flagEdit = false
    private lateinit var id: String
    private lateinit var editTitle: String
    private lateinit var editContent: String

    private val viewModel: NoteViewModel by viewModels { viewModelFactory.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = getString(R.integer.transition_duration).toLong()
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
        val args: EditFragmentArgs by navArgs()
        id = args.id
        flagEdit = args.edit
        binding.editCardView.transitionName = args.transitionName

        val datestamp =
            SimpleDateFormat(getString(R.string.date_format)).format(System.currentTimeMillis())
        if (flagEdit) {
            viewModel.getNoteById(id).observeOnce(viewLifecycleOwner) { note ->
                editTitle = note.title
                editContent = note.content
                binding.editInputTitle.editText?.setText(editTitle)
                binding.editInputContent.editText?.setText(editContent)
                if (datestamp == note.datestamp) {
                    binding.editTime.text = "${getString(R.string.label_edit)}  ${note.timestamp}"
                } else {
                    binding.editTime.text = "${getString(R.string.label_edit)} ${note.datestamp}"
                }
            }
        } else {
            binding.editTime.text = "${getString(R.string.label_edit)}  ${
                SimpleDateFormat(getString(R.string.time_format)).format(System.currentTimeMillis())
            }"
        }

        binding.editFragmentReturn.setOnClickListener { findNavController().popBackStack() }

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
        val datestamp =
            SimpleDateFormat(getString(R.string.date_format)).format(System.currentTimeMillis())
        val timestamp =
            SimpleDateFormat(getString(R.string.time_format)).format(System.currentTimeMillis())
        val note = Note(
            id.toInt(),
            title = title,
            content = content,
            datestamp = datestamp,
            timestamp = timestamp
        )
        if (flagEdit && (editTitle != title || editContent != content)) viewModel.update(note)
        else if (!flagEdit && (title.isNotEmpty() || content.isNotEmpty())) viewModel.insert(note)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}