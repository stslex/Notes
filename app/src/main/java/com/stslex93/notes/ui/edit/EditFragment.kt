package com.stslex93.notes.ui.edit

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex93.notes.R
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.databinding.FragmentEditBinding
import com.stslex93.notes.ui.NoteViewModel
import com.stslex93.notes.utilites.BaseFragment
import com.stslex93.notes.utilites.hideKeyBoard
import java.text.SimpleDateFormat

class EditFragment : BaseFragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private var flagEdit = false
    private lateinit var id: String

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
        requireActivity().hideKeyBoard()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val args: EditFragmentArgs by navArgs()
        id = args.id
        flagEdit = args.edit
        if (flagEdit) {
            binding.editCardView.transitionName = id
            viewModel.note(id).observe(viewLifecycleOwner) { note ->
                binding.editInputTitle.editText?.setText(note.title)
                binding.editInputContent.editText?.setText(note.content)
            }
        } else binding.editCardView.transitionName = getString(R.string.default_transition_name)

    }

    override fun onStop() {
        super.onStop()
        saveData()
    }

    private fun saveData() {
        val title = binding.editInputTitle.editText?.text.toString()
        val content = binding.editInputContent.editText?.text.toString()

        val datestamp =
            SimpleDateFormat(getString(R.string.date_format)).format(System.currentTimeMillis())
        val timestamp =
            SimpleDateFormat(getString(R.string.time_format)).format(System.currentTimeMillis())

        if (flagEdit) {
            val note = Note(id.toInt(), title, content, datestamp, timestamp)
            viewModel.update(note)
        } else if (title.isNotEmpty() || content.isNotEmpty()) {
            val note =
                Note(
                    id.toInt(),
                    title = title,
                    content = content,
                    datestamp = datestamp,
                    timestamp = timestamp
                )
            viewModel.insert(note)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}