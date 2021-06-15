package com.example.notes.ui.edit

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.NoteApplication
import com.example.notes.NoteViewModel
import com.example.notes.NoteViewModelFactory
import com.example.notes.R
import com.example.notes.databinding.FragmentEditBinding
import com.example.notes.model.base.Note
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import java.text.SimpleDateFormat

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding

    private var flagEdit = false
    private lateinit var note: Note
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((activity?.application as NoteApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 1000
        }
        sharedElementReturnTransition = MaterialContainerTransform().apply {
            duration = 1000
        }

        enterTransition = MaterialElevationScale(true).apply {
            duration = 1000
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        val imm: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity?.window?.decorView?.windowToken, 0)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: EditFragmentArgs by navArgs()
        val note = args.note
        val key = args.key
        binding.editCardView.transitionName = key
        flagEdit = args.edit
        if (note != null) {
            this.note = note
            if ((note.title!="") && (note.content!="")){
                binding.editInputTitle.editText?.setText(note.title)
                binding.editInputContent.editText?.setText(note.content)
            }
        }

        initRecyclerImage()

    }

    private fun initRecyclerImage() {
        val recycler = binding.editInputRecyclerImage
        val adapter = ImageAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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
            Log.i("Snackbar reader::", "1")

        } else {
            if (title.isNotEmpty() || content.isNotEmpty()) {
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
    }

}