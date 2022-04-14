package com.stslex.notes.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.stslex.notes.R
import com.stslex.notes.appComponent
import com.stslex.notes.databinding.FragmentMainBinding
import com.stslex.notes.ui.main.adapter.MainAdapter
import com.stslex.notes.ui.main.utils.NotesDiffItemCallback
import com.stslex.notes.ui.main.utils.OnNoteClickListener
import com.stslex.notes.ui.main.utils.OnNoteLongClickListener
import com.stslex.notes.ui.main.utils.SelectorNoteItemsUtil
import com.stslex.notes.ui.model.NoteUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = checkNotNull(_binding)

    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var noteClicker: OnNoteClickListener
    private lateinit var noteLongClickListener: OnNoteLongClickListener
    private lateinit var itemsSelector: SelectorNoteItemsUtil

    @Inject
    fun injection(
        viewModelFactory: ViewModelProvider.Factory,
        noteClicker: OnNoteClickListener.Factory,
        noteLongClickListener: OnNoteLongClickListener.Factory,
        itemsSelector: SelectorNoteItemsUtil
    ) {
        this.viewModelFactory = viewModelFactory
        this.itemsSelector = itemsSelector
        this.noteClicker = noteClicker.create(itemsSelector)
        this.noteLongClickListener = noteLongClickListener.create(itemsSelector)
    }

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private val adapter: MainAdapter by lazy {
        MainAdapter(noteClicker, noteLongClickListener, NotesDiffItemCallback())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.transition_duration).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

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
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.notes.collect(adapter::submitData)
        }
        binding.searchView.setOnQueryTextListener(queryTextListener)
        binding.recyclerView.adapter = adapter
        postponeEnterTransition()
        binding.recyclerView.doOnPreDraw { startPostponedEnterTransition() }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            itemsSelector.itemsSelected.collect(::itemsSelectedCollect)
        }
    }

    private val queryTextListener: SearchView.OnQueryTextListener
        get() = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.setQuery(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.setQuery(newText)
                return false
            }
        }

    @SuppressLint("ResourceType")
    private fun itemsSelectedCollect(items: List<NoteUIModel>) {
        if (items.isEmpty()) setFabAdd()
        else setFubDelete()
    }

    private var deleteJob: Job? = null
    private fun setFabAdd() {
        deleteJob?.cancel()
        val icon: Icon = Icon.createWithResource(requireContext(), R.drawable.ic_baseline_add_24)
        binding.fab.setImageIcon(icon)
        binding.fab.setOnClickListener(noteClicker::createNewNote)
    }

    private fun setFubDelete() {
        val icon: Icon = Icon.createWithResource(requireContext(), R.drawable.ic_baseline_remove_24)
        binding.fab.setImageIcon(icon)
        binding.fab.setOnClickListener(fabDeleteClickListener)
    }

    private val fabDeleteClickListener = View.OnClickListener {
        deleteJob?.cancel()
        deleteJob = viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            itemsSelector.itemsSelected.collect { items ->
                val listOfIds: List<Int> = items.map { it.id() }
                viewModel.deleteNotesByIds(ids = listOfIds)
                itemsSelector.deleteAll()
            }
        }
        showSuccess()
    }

    private fun showSuccess() {
        val theme = resources.newTheme()
        val color = resources.getColor(R.color.success, theme)
        Snackbar.make(requireView(), "Notes Deleted", Snackbar.LENGTH_SHORT).apply {
            animationMode = Snackbar.ANIMATION_MODE_SLIDE
            setBackgroundTint(color)
            setActionTextColor(Color.BLACK)
            setTextColor(Color.BLACK)
            setAction("CANCEL") {
                viewModel.insertAll(itemsSelector.lastSelectedItems)
            }
        }.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        itemsSelector.deleteAll()
        _binding = null
        deleteJob?.cancel()
    }
}

