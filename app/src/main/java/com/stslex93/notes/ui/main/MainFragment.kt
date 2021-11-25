package com.stslex93.notes.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
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
import com.stslex93.notes.R
import com.stslex93.notes.appComponent
import com.stslex93.notes.core.Resource
import com.stslex93.notes.databinding.FragmentMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigationViewBinder: BottomNavigationViewBinder.Factory

    @Inject
    lateinit var noteClicker: OnNoteClickListener

    @Inject
    lateinit var noteLongClickListener: OnNoteLongClickListener

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
        initNavigationView()
        initRecyclerView()
        binding.fab.setOnClickListener(noteClicker::createNewNote)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            selectedItems.collect(::itemsSelectedCollect)
        }
    }

    @SuppressLint("ResourceType")
    private fun itemsSelectedCollect(items: List<NoteUI>) {
        if (items.isEmpty())
            binding.fab.show()
        else
            binding.fab.hide()
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
    }
}

