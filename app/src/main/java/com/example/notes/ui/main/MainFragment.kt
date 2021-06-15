package com.example.notes.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.NoteApplication
import com.example.notes.NoteViewModel
import com.example.notes.NoteViewModelFactory
import com.example.notes.R
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.model.base.Note
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MainAdapter
    private var CARD_CHECKING = false
    private val checkNotes = mutableListOf<Note>()
    private val checkCards = mutableListOf<MaterialCardView>()
    private lateinit var fab: FloatingActionButton
    private lateinit var appBar: BottomAppBar

    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((activity?.application as NoteApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val imm: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity?.window?.decorView?.windowToken, 0)

        fab = binding.fragmentMainFloatingActionButton
        appBar = binding.fragmentMainBottomAppBar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab.setOnClickListener(this)
        initRecyclerView()
        recyclerViewManagerChanger()
    }

    private fun recyclerViewManagerChanger() {
        val appBarChangeManager = appBar.menu.findItem(R.id.appbar_change_recycler_manager)

        val linerManager = LinearLayoutManager(context)
        val gridManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        val theme = this.activity?.theme
        val iconLiner =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_view_stream_24, theme)

        val iconGrid =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_view_module_24, theme)


        appBarChangeManager.setOnMenuItemClickListener {
            if (recycler.layoutManager == linerManager) {
                recycler.layoutManager = gridManager
                it.icon = iconLiner
            } else {
                recycler.layoutManager = linerManager
                it.icon = iconGrid
            }

            true
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

    private val clickListener = ItemClickListener(
        { card, note, key ->
            if (CARD_CHECKING) {
                checkCardClick(card, note)
            } else {
                val direction: NavDirections =
                    MainFragmentDirections.actionNavHomeToEditFragment(note, true, key)
                card.isTransitionGroup = true
                val extras = FragmentNavigatorExtras(card to key)
                findNavController().navigate(direction, extras)
            }

        }, { card, note ->
            checkCardLongClick(card, note)
        })

    private fun firstLongInitClick() {
        checkCards.clear()
        checkNotes.clear()
        CARD_CHECKING = true
        statusCheckingVisible()
    }

    private fun checkCardClick(card: MaterialCardView, note: Note) {
        if (card.isChecked) {
            cardCheckRemove(card, note)
        } else if (!card.isChecked) {
            cardCheckAdd(card, note)
        }
    }

    private fun checkCardLongClick(card: MaterialCardView, note: Note) {
        if (card.isChecked) {
            cardCheckRemove(card, note)
        } else if (!card.isChecked) {
            if (!CARD_CHECKING) {
                firstLongInitClick()
            }
            cardCheckAdd(card, note)
        }
    }

    private fun cardCheckRemove(card: MaterialCardView, note: Note) {
        card.isChecked = false
        checkCards.remove(card)
        checkNotes.remove(note)
        if (checkCards.isEmpty()) {
            statusPrimaryVisible()
        }
    }

    private fun cardCheckAdd(card: MaterialCardView, note: Note) {
        card.isChecked = true
        checkCards.add(card)
        checkNotes.add(note)
    }

    override fun onClick(v: View?) {
        when (v) {
            fab -> {
                if (!CARD_CHECKING) {
                    val note = Note(title = "", content = "", datestamp = "", timestamp = "")
                    val direction: NavDirections =
                        MainFragmentDirections.actionNavHomeToEditFragment(
                            note,
                            false,
                            key = "trait"
                        )
                    val fabKey = getString(R.string.transition_fab_key)
                    val extras = FragmentNavigatorExtras(fab to "transit")
                    findNavController().navigate(direction, extras)
                } else {
                    noteViewModel.deleteNotes(checkNotes)
                    checkCards.forEach { it.isChecked = false }

                    val snackBar =
                        Snackbar.make(binding.root, "Successful deleted", Snackbar.LENGTH_SHORT)
                    snackBar.anchorView = fab
                    snackBar.setAction("cancel") {
                        noteViewModel.insertAll(checkNotes)
                        val snackBar = Snackbar.make(it, "It's Canceled", Snackbar.LENGTH_SHORT)
                        snackBar.anchorView = fab
                        snackBar.show()
                    }
                    snackBar.show()

                    statusPrimaryVisible()

                }
            }

        }
    }

    private fun statusCheckingVisible() {
        appBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        val theme = resources.newTheme()
        val iconDrawable =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_remove_24, theme)
        fab.setImageDrawable(iconDrawable)
    }

    private fun statusPrimaryVisible() {
        CARD_CHECKING = false
        appBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        val theme = resources.newTheme()
        val iconDrawable =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_add_24, theme)
        fab.setImageDrawable(iconDrawable)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val searchViewItem = menu?.findItem(R.id.toolbar_main_menu_search)
        val searchView = searchViewItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                noteViewModel.allNotes.observe(viewLifecycleOwner){
                    val list = mutableListOf<Note>()
                    val filter = newText?.lowercase(Locale.getDefault())
                    it.forEach { note ->
                        val title = note.title.lowercase(Locale.getDefault())
                        val content = note.title.lowercase(Locale.getDefault())

                        if (title.contains(filter.toString()) || content.contains(filter.toString())){
                            if (!list.contains(note)) list.add(note)
                        }
                    }
                    adapter.setNotes(list)
                    adapter.notifyDataSetChanged()
                }

                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }


}