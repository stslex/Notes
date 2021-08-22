package com.stslex93.notes.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.core.view.doOnPreDraw
import androidx.core.view.forEach
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.card.MaterialCardView
import com.google.android.material.math.MathUtils
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
        initBottomNavigation()
        initFields()
        initRecyclerView()
    }

    private fun initBottomNavigation() {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomNavView)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        binding.bottomBar.setNavigationOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                binding.fab.show()
                binding.scrim.visibility = View.GONE
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                binding.fab.hide()
                binding.scrim.visibility = View.VISIBLE
            }
        }

        binding.bottomNavView.setNavigationItemSelectedListener { menuItem ->
            binding.bottomNavView.menu.forEach { it.isChecked = false }
            menuItem.isChecked = true
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            true
        }

        binding.scrim.setOnClickListener {
            binding.fab.show()
            binding.scrim.visibility = View.GONE
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val baseColor = Color.BLACK
                // 60% opacity
                val baseAlpha =
                    ResourcesCompat.getFloat(resources, R.dimen.material_emphasis_medium)
                // Map slideOffset from [-1.0, 1.0] to [0.0, 1.0]
                val offset = (slideOffset - (-1f)) / (1f - (-1f)) * (1f - 0f) + 0f
                val alpha = MathUtils.lerp(0f, 255f, offset * baseAlpha).toInt()
                val color = Color.argb(alpha, baseColor.red, baseColor.green, baseColor.blue)
                binding.scrim.setBackgroundColor(color)
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    binding.fab.show()
                    binding.scrim.visibility = View.GONE
                }
            }
        })
    }

    private fun initFields() {
        requireActivity().hideKeyboard()
        setHasOptionsMenu(true)
        binding.fab.setOnClickListener(this)
        mainNoteClick.isChecking.observe(viewLifecycleOwner) {
            if (it) {
                binding.bottomBar.menu.clear()
                binding.bottomBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.bottomBar.inflateMenu(R.menu.bottom_menu_remove)
                binding.fab.setImageDrawable(getDrawableIcon(R.drawable.ic_baseline_remove_24))
            } else {
                binding.fab.setImageDrawable(getDrawableIcon(R.drawable.ic_baseline_add_24))
                binding.bottomBar.apply {
                    menu.clear()
                    fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                    inflateMenu(R.menu.bottom_appbar)
                    setOnMenuItemClickListener(menuItemClickListener)
                }
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
        val direction = MainFragmentDirections.actionNavHomeToNavEdit(
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
                    mainNoteClick.apply { card.checkCardClick() }
                } else {
                    card.navigation(id = card.transitionName, edit = true)
                }
            }

        }, { card ->
            mainNoteClick.apply { card.checkCardClick() }
        })

    private val BottomAppBar.menuItemClickListener: Toolbar.OnMenuItemClickListener
        get() = Toolbar.OnMenuItemClickListener {
            when (it.itemId) {
                R.id.appbar_search -> {
                    transitionName = getString(R.string.search_transition_name)
                    val directions = MainFragmentDirections.actionNavHomeToNavSearch()
                    val extras =
                        FragmentNavigatorExtras(this to transitionName)
                    findNavController().navigate(directions, extras)
                    false
                }
                R.id.appbar_change_layout_manager -> {
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
                else -> {
                    false
                }
            }
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

