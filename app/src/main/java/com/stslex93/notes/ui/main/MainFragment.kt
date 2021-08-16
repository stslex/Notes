package com.stslex93.notes.ui.main

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
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

class MainFragment : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MainAdapter
    private var isChecking = false
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
        onMenuItemClick()
    }

    private fun initFields() {
        requireActivity().hideKeyBoard()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.fab.setOnClickListener {
            if (!isChecking) {
                val note = Note(title = "", content = "", datestamp = "", timestamp = "")
                it.navigation(note.id.toString(), false)
            } else {
                noteViewModel.getNotesByIds(mainNoteClick.checkNotes)
                    .observeOnce(viewLifecycleOwner) { insertList ->
                        noteViewModel.deleteNotesByIds(mainNoteClick.checkNotes)
                        mainNoteClick.checkCards.forEach { card -> card.isChecked = false }
                        it.showSnackBar(
                            getString(R.string.label_successful_deleted),
                            getString(R.string.label_cancel)
                        ) {
                            noteViewModel.insertAll(insertList)
                            it.showSnackBar(
                                getString(R.string.label_successful_canceled),
                                getString(R.string.label_ok)
                            ) {}
                        }
                    }

                statusPrimaryVisible()
            }

        }
    }

    private fun initRecyclerView() {
        recycler = binding.mainFragmentRecyclerView
        adapter = MainAdapter(clickListener)
        noteViewModel.allNotes.observe(viewLifecycleOwner) {
            adapter.setNotes(it)
        }
        recycler.adapter = adapter
        recycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        postponeEnterTransition()
        recycler.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    private fun View.navigation(id: String, edit: Boolean) {
        val direction =
            MainFragmentDirections.actionNavHomeToEditFragment(
                id,
                edit
            )
        if (this is MaterialCardView) this.isTransitionGroup = true
        val extras = FragmentNavigatorExtras(this to this.transitionName)
        findNavController().navigate(direction, extras)
    }

    private val clickListener = ItemClickListener(
        { card, id ->
            if (isChecking) {
                checkCardClick(card, id)
            } else {
                card.navigation(id, true)
            }

        }, { card, id ->
            checkCardLongClick(card, id)
        })

    private fun firstLongInitClick() {
        mainNoteClick.checkCards.clear()
        mainNoteClick.checkNotes.clear()
        statusCheckingVisible()
    }

    private fun checkCardClick(card: MaterialCardView, id: String) {
        if (card.isChecked) {
            mainNoteClick.cardCheckRemove(card, id) { statusPrimaryVisible() }
        } else {
            mainNoteClick.cardCheckAdd(card, id)
        }
    }

    private fun checkCardLongClick(card: MaterialCardView, id: String) {
        if (card.isChecked) {
            mainNoteClick.cardCheckRemove(card, id) { statusPrimaryVisible() }
        } else {
            if (!isChecking) {
                firstLongInitClick()
            }
            mainNoteClick.cardCheckAdd(card, id)
        }
    }

    private fun statusCheckingVisible() {
        isChecking = !isChecking
        binding.bottomNavigation.menu.clear()
        binding.bottomBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        binding.bottomNavigation.inflateMenu(R.menu.bottom_menu_remove)
        binding.fab.setImageDrawable(getDrawableIcon(R.drawable.ic_baseline_remove_24))
    }

    private fun statusPrimaryVisible() {
        isChecking = !isChecking
        binding.bottomNavigation.menu.clear()

        binding.bottomBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        binding.bottomNavigation.inflateMenu(R.menu.bottom_menu)
        onMenuItemClick()
        binding.fab.setImageDrawable(getDrawableIcon(R.drawable.ic_baseline_add_24))
    }

    private fun onMenuItemClick() {
        val itemLayout = binding.bottomNavigation.menu.findItem(R.id.action_change_layout)
        val itemTheme = binding.bottomNavigation.menu.findItem(R.id.action_change_theme)

        itemTheme.setOnMenuItemClickListener {
            binding.fab.showSnackBar(
                text = getString(R.string.label_be_soon),
                textAction = getString(R.string.label_ok)
            ) {}
            false
        }
        val linearManager = LinearLayoutManager(requireContext())
        val gridManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)

        itemLayout.setOnMenuItemClickListener {
            if (recycler.layoutManager == linearManager) {
                recycler.layoutManager = gridManager
                it.icon = getDrawableIcon(R.drawable.ic_baseline_view_module_24)
                it.title = getString(R.string.label_grid)
            } else {
                recycler.layoutManager = linearManager
                it.icon = getDrawableIcon(R.drawable.ic_baseline_view_stream_24)
                it.title = getString(R.string.label_linear)
            }
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        (menu.findItem(R.id.toolbar_main_menu_search)
            .actionView as SearchView)
            .let {
                it.setOnQueryTextListener(OnQueryTextListener(it) { newText ->
                    noteViewModel.allNotes.observe(viewLifecycleOwner) { list ->
                        adapter.setNotes(
                            list.filter { note ->
                                note.title.lowerContains(newText) || note.content.lowerContains(
                                    newText
                                )
                            }
                        )
                    }
                })
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        noteViewModel.allNotes.removeObservers(viewLifecycleOwner)
    }
}

