package com.stslex93.notes.ui.search

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.card.MaterialCardView
import com.google.android.material.transition.MaterialContainerTransform
import com.stslex93.notes.R
import com.stslex93.notes.databinding.FragmentSearchBinding
import com.stslex93.notes.ui.NoteViewModel
import com.stslex93.notes.ui.main.adapter.MainAdapter
import com.stslex93.notes.utilites.BaseFragment
import com.stslex93.notes.utilites.OnQueryTextListener
import com.stslex93.notes.utilites.clicker.ItemClickListener

class SearchFragment : BaseFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var gridLayoutManager: StaggeredGridLayoutManager
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSearchView()
    }

    private fun initSearchView() {
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(
            OnQueryTextListener(searchView) { newText ->
                noteViewModel.allNotes.observe(viewLifecycleOwner) { list ->
                    adapter.setNotes(
                        list.filter { it.checkTitleContentContains(newText) }
                    )
                }
            })
    }

    private fun initRecyclerView() {
        recycler = binding.searchFragmentRecyclerView
        adapter = MainAdapter(clickListener)
        gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        noteViewModel.allNotes.observe(viewLifecycleOwner) {
            adapter.setNotes(it)
        }
        recycler.adapter = adapter
        recycler.layoutManager = gridLayoutManager
        postponeEnterTransition()
        recycler.doOnPreDraw {
            startPostponedEnterTransition()
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
                edit
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