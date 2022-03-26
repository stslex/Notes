package com.stslex.notes.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.stslex.notes.R
import com.stslex.notes.appComponent
import com.stslex.notes.core.Resource
import com.stslex.notes.databinding.FragmentMainBinding
import com.stslex.notes.ui.main.adapter.MainAdapter
import com.stslex.notes.ui.main.utils.OnNoteClickListener
import com.stslex.notes.ui.main.utils.OnNoteLongClickListener
import com.stslex.notes.ui.model.NoteUI
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var navigationViewBinder: BottomNavigationViewBinder.Factory
    private lateinit var noteClicker: OnNoteClickListener
    private lateinit var noteLongClickListener: OnNoteLongClickListener

    @Inject
    fun injection(
        viewModelFactory: ViewModelProvider.Factory,
        navigationViewBinder: BottomNavigationViewBinder.Factory,
        noteClicker: OnNoteClickListener,
        noteLongClickListener: OnNoteLongClickListener
    ) {
        this.viewModelFactory = viewModelFactory
        this.navigationViewBinder = navigationViewBinder
        this.noteClicker = noteClicker
        this.noteLongClickListener = noteLongClickListener
    }

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private val adapter: MainAdapter by lazy {
        MainAdapter(noteClicker, noteLongClickListener)
    }

    private val selectedItems: SharedFlow<List<NoteUI>> by lazy {
        noteLongClickListener.itemsSelected
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
            viewModel.getAllNotes().collect(::collector)
        }
        initRecyclerView()
        initNavigationView()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            selectedItems.collect(::itemsSelectedCollect)
        }
    }


    @SuppressLint("ResourceType")
    private fun itemsSelectedCollect(items: List<NoteUI>) {
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
            noteLongClickListener.itemsSelected.collect { items ->
                val listOfIds: List<Int> = items.map { it.getId() }
                viewModel.deleteNotesByIds(ids = listOfIds)
                noteLongClickListener.deleteAll()
            }
        }
    }

    private fun initNavigationView() = with(binding) {
        navigationViewBinder.create(
            navigationView = bottomNavView,
            bottomAppBar = bottomBar,
            scrim = scrim,
            fab = fab
        ).bind()
    }

    private fun initRecyclerView() = with(binding.recyclerView) {
        adapter = this@MainFragment.adapter
        val orientation = StaggeredGridLayoutManager.VERTICAL
        layoutManager = StaggeredGridLayoutManager(2, orientation)
        postponeEnterTransition()
        doOnPreDraw { startPostponedEnterTransition() }
    }

    private suspend fun collector(resource: Resource<List<NoteUI>>): Unit =
        withContext(Dispatchers.Main) {
            when (resource) {
                is Resource.Success -> setNotes(resource.data)
                is Resource.Failure -> hideProgress()
                is Resource.Loading -> showProgress()
            }
        }

    private fun setNotes(notes: List<NoteUI>) {
        hideProgress()
        adapter.setItems(notes)
    }

    private fun showProgress() {
        binding.SHOWPROGRESS.apply {
            visibility = View.VISIBLE
        }
    }

    private fun hideProgress() {
        binding.SHOWPROGRESS.apply {
            visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        deleteJob?.cancel()
    }
}

