package com.stslex93.notes.ui.search

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.card.MaterialCardView
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex93.notes.R
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.databinding.FragmentSearchBinding
import com.stslex93.notes.ui.NoteViewModel
import com.stslex93.notes.ui.main.MainAdapter
import com.stslex93.notes.utilites.BaseFragment
import com.stslex93.notes.utilites.Response
import com.stslex93.notes.utilites.clicker.ItemClickListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchFragment : BaseFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var gridLayoutManager: StaggeredGridLayoutManager
    private val noteViewModel: NoteViewModel by viewModels { viewModelFactory.get() }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = getString(R.integer.transition_duration).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initRecyclerView()
        binding.searchView.apply {
            //setOnQueryTextListener(listener)
        }
        binding.searchFragmentReturn.setOnClickListener { findNavController().popBackStack() }
    }

//    private val SearchView.listener: OnQueryTextListener
//        get() = OnQueryTextListener(this) { newText ->
//            getNotes {
//                adapter.setNotes(
//                    it.filter { note ->
//                        note.checkTitleContentContains(newText)
//                    }
//                )
//            }
//        }

//    private fun initRecyclerView() {
//        recycler = binding.searchFragmentRecyclerView
//        adapter = MainAdapter(clickListener)
//        gridLayoutManager =
//            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        getNotes {
//            adapter.setNotes(it)
//        }
//        recycler.adapter = adapter
//        recycler.layoutManager = gridLayoutManager
//        postponeEnterTransition()
//        recycler.doOnPreDraw {
//            startPostponedEnterTransition()
//        }
//    }

    private inline fun getNotes(crossinline success: (List<Note>) -> Unit) =
        viewLifecycleOwner.lifecycleScope.launch {
            noteViewModel.allNotes().collect {
                when (it) {
                    is Response.Success -> {
                        success(it.data)
                    }
                    is Response.Failure -> {
                        Log.e(
                            "MainScreen GetAllNotes",
                            it.exception.message,
                            it.exception.cause
                        )
                    }
                    is Response.Loading -> {

                    }
                }
            }
        }

    private val clickListener = ItemClickListener(
        { card ->
            card.navigation(id = card.transitionName, edit = true)
        }, {})

    private fun View.navigation(id: String, edit: Boolean) {
        val direction =
            SearchFragmentDirections.actionNavSearchToNavEdit(
                id,
                edit,
                id
            )
        if (this is MaterialCardView) this.isTransitionGroup = true
        val extras = FragmentNavigatorExtras(this to this.transitionName)
        findNavController().navigate(direction, extras)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}