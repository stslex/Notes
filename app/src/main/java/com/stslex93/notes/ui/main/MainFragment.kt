package com.stslex93.notes.ui.main

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.card.MaterialCardView
import com.stslex93.notes.R
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.databinding.FragmentMainBinding
import com.stslex93.notes.ui.NoteViewModel
import com.stslex93.notes.ui.main.adapter.MainAdapter
import com.stslex93.notes.utilites.*
import com.stslex93.notes.utilites.clicker.ItemClickListener

class MainFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var gridLayoutManager: StaggeredGridLayoutManager
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val mainNoteClick = MainNoteClick()
    private val noteViewModel: NoteViewModel by viewModels { viewModelFactory.get() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
        initRecyclerView()
    }

    private fun initFields() {
        requireActivity().hideKeyBoard()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.fab.setOnClickListener(this)

        mainNoteClick.isChecking.observe(viewLifecycleOwner) {
            binding.bottomNavigation.menu.clear()
            if (it) {
                binding.bottomBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.bottomNavigation.inflateMenu(R.menu.bottom_menu_remove)
                binding.fab.setImageDrawable(getDrawableIcon(R.drawable.ic_baseline_remove_24))
            } else {
                binding.bottomBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.bottomNavigation.inflateMenu(R.menu.bottom_menu)
                binding.fab.setImageDrawable(getDrawableIcon(R.drawable.ic_baseline_add_24))
                onMenuItemClick()
            }
        }
    }

    private fun initRecyclerView() {
        recycler = binding.mainFragmentRecyclerView
        adapter = MainAdapter(clickListener)
        gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        linearLayoutManager = LinearLayoutManager(requireContext())
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

    private fun View.navigation(id: String, edit: Boolean) {
        val direction =
            MainFragmentDirections.actionNavHomeToNavEdit(
                id,
                edit
            )
        if (this is MaterialCardView) this.isTransitionGroup = true
        val extras = FragmentNavigatorExtras(this to this.transitionName)
        findNavController().navigate(direction, extras)
    }

    private val clickListener = ItemClickListener(
        { card ->
            mainNoteClick.isChecking.observeOnce(viewLifecycleOwner) {
                if (it) {
                    mainNoteClick.checkCardClick(card = card)
                } else {
                    card.navigation(id = card.transitionName, edit = true)
                }
            }

        }, { card ->
            mainNoteClick.checkCardClick(card = card)
        })

    private fun onMenuItemClick() {
        val itemLayout = binding.bottomNavigation.menu.findItem(R.id.action_change_layout)
        val itemTheme = binding.bottomNavigation.menu.findItem(R.id.action_search)

        itemTheme.setOnMenuItemClickListener {
            it.actionView.transitionName = getString(R.string.search_transition_name)
            val directions = MainFragmentDirections.actionNavHomeToNavSearch()
            val extras = FragmentNavigatorExtras(it.actionView to it.actionView.transitionName)
            findNavController().navigate(directions, extras)
            false
        }

        itemLayout.setOnMenuItemClickListener {
            if (recycler.layoutManager == linearLayoutManager) {
                recycler.layoutManager = gridLayoutManager
                it.icon = getDrawableIcon(R.drawable.ic_baseline_view_module_24)
                it.title = getString(R.string.label_grid)
            } else {
                recycler.layoutManager = linearLayoutManager
                it.icon = getDrawableIcon(R.drawable.ic_baseline_view_stream_24)
                it.title = getString(R.string.label_linear)
            }
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val searchView: SearchView =
            menu.findItem(R.id.toolbar_main_menu_search).actionView as SearchView
        searchView.setOnQueryTextListener(
            OnQueryTextListener(searchView) { newText ->
                noteViewModel.allNotes.observe(viewLifecycleOwner) { list ->
                    adapter.setNotes(
                        list.filter { it.checkTitleContentContains(newText) }
                    )
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        noteViewModel.allNotes.removeObservers(viewLifecycleOwner)
        mainNoteClick.apply {
            isChecking.removeObservers(viewLifecycleOwner)
            clearCheck()
            deleteAll()
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.fab -> {
                mainNoteClick.isChecking.observeOnce(viewLifecycleOwner) {
                    if (it) {
                        val checkNotes = mainNoteClick.checkNotesId
                        noteViewModel.getNotesByIds(checkNotes)
                            .observeOnce(viewLifecycleOwner) { insertList ->
                                noteViewModel.deleteNotesByIds(checkNotes)
                                mainNoteClick.clearCheck()
                                p0.showSnackBar(
                                    getString(R.string.label_successful_deleted),
                                    getString(R.string.label_cancel)
                                ) {
                                    noteViewModel.insertAll(insertList)
                                    p0.showSnackBar(
                                        getString(R.string.label_successful_canceled),
                                        getString(R.string.label_ok)
                                    ) {}
                                }
                                mainNoteClick.deleteAll()
                            }
                    } else {
                        val note = Note(title = "", content = "", datestamp = "", timestamp = "")
                        p0.navigation(note.id.toString(), false)
                    }
                }
            }
        }
    }
}

