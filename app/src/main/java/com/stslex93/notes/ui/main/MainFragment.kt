package com.stslex93.notes.ui.main

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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.stslex93.notes.R
import com.stslex93.notes.appComponent
import com.stslex93.notes.databinding.FragmentMainBinding
import com.stslex93.notes.ui.main.adapter.MainAdapter
import com.stslex93.notes.ui.main.utils.*
import com.stslex93.notes.ui.model.NoteUIModel
import com.stslex93.notes.ui.utils.snackbar.SnackBarUtil
import kotlinx.coroutines.*
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
    private lateinit var queryTextListenerFactory: QueryTextListener.Factory
    private lateinit var snackBarUtil: SnackBarUtil

    @Inject
    fun injection(
        viewModelFactory: ViewModelProvider.Factory,
        noteClicker: OnNoteClickListener.Factory,
        noteLongClickListener: OnNoteLongClickListener.Factory,
        itemsSelector: SelectorNoteItemsUtil,
        queryTextListenerFactory: QueryTextListener.Factory,
        snackBarUtil: SnackBarUtil
    ) {
        this.viewModelFactory = viewModelFactory
        this.itemsSelector = itemsSelector
        this.noteClicker = noteClicker.create(itemsSelector)
        this.noteLongClickListener = noteLongClickListener.create(itemsSelector)
        this.queryTextListenerFactory = queryTextListenerFactory
        this.snackBarUtil = snackBarUtil
    }

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private val adapter: MainAdapter by lazy {
        MainAdapter(noteClicker, noteLongClickListener, NotesDiffItemCallback())
    }

    private val queryTextListener: SearchView.OnQueryTextListener by lazy {
        queryTextListenerFactory.create {
            itemsSelector.deleteAll()
            viewModel.setQuery(it)
        }
    }

    private var deleteJob: Job = Job()

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

    @SuppressLint("ResourceType")
    private fun itemsSelectedCollect(items: List<NoteUIModel>) {
        if (items.isEmpty()) setFabAdd()
        else setFubDelete()
    }

    private fun setFabAdd() {
        deleteJob.cancel()
        val icon: Icon = Icon.createWithResource(requireContext(), R.drawable.ic_baseline_add_24)
        binding.fab.setImageIcon(icon)
        binding.fab.setOnClickListener(noteClicker::createNewNote)
    }

    private fun setFubDelete() {
        val icon: Icon = Icon.createWithResource(requireContext(), R.drawable.ic_baseline_remove_24)
        binding.fab.setImageIcon(icon)
        binding.fab.setOnClickListener(fabDeleteClickListener)
    }

    private val fabDeleteClickListener: View.OnClickListener = View.OnClickListener {
        deleteJob.invokeOnCompletion {
            deleteJob = viewLifecycleOwner.lifecycleScope.launch(
                context = Dispatchers.IO,
                block = deleteBlock()
            )
            snackBarUtil.showSuccess(requireView()) {
                viewModel.insertAll(itemsSelector.lastSelectedItems)
            }
        }
    }

    private fun deleteBlock(): suspend CoroutineScope.() -> Unit = {
        itemsSelector.itemsSelected.collect { items ->
            viewModel.deleteNotesByIds(items)
            itemsSelector.deleteAll()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        itemsSelector.deleteAll()
        deleteJob.cancel()
        _binding = null
    }
}