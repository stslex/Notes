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
    private lateinit var note: Note

    private val noteViewModel: NoteViewModel by viewModels { viewModelFactory.get() }

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
        binding.editCardView.transitionName = args.transitionName
        flagEdit = args.edit
        args.note?.let {
            this.note = it
            if (flagEdit) {
                binding.editInputTitle.editText?.setText(it.title)
                binding.editInputContent.editText?.setText(it.content)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        saveData()
    }

    private fun saveData() {
        val title = binding.editInputTitle.editText?.text.toString()
        val content = binding.editInputContent.editText?.text.toString()

        val datestamp = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
        val timestamp = SimpleDateFormat("kk.mm").format(System.currentTimeMillis())

        if (flagEdit) {
            val note = Note(note.id, title, content, datestamp, timestamp)
            noteViewModel.update(note)
        } else if (title.isNotEmpty() || content.isNotEmpty()) {
            val note =
                Note(
                    note.id,
                    title = title,
                    content = content,
                    datestamp = datestamp,
                    timestamp = timestamp
                )
            noteViewModel.insert(note)
            binding.editCardView.transitionName = "newTransition"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}