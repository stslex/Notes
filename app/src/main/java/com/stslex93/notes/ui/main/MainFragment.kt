package com.stslex93.notes.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.stslex93.notes.appComponent
import com.stslex93.notes.core.Resource
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.databinding.FragmentMainBinding
import com.stslex93.notes.ui.core.ClickListener
import com.stslex93.notes.ui.core.LongClickListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
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

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private val adapter: MainAdapter by lazy {
        MainAdapter(OnClick(), OnLongClick())
    }

    @ExperimentalCoroutinesApi
    private val getNotes: Job by lazy {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getAllNotes().collect {
                when (it) {
                    is Resource.Success -> setNotes(it.data)
                    is Resource.Failure -> progress()
                    is Resource.Loading -> progress()
                }
            }
        }
    }

    private fun setNotes(notes: List<NoteUI>) {
        progress()
        adapter.setNotes(notes)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().appComponent.inject(this)
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
        with(binding) {
            navigationViewBinder.create(
                navigationView = bottomNavView,
                bottomAppBar = bottomBar,
                scrim = scrim,
                fab = fab
            ).bind()
            recyclerView.adapter = adapter
            fab.setOnClickListener(fabClickListener)
        }
    }

    private inner class OnClick : ClickListener<NoteUI> {

        override fun onClick(item: NoteUI) {
            item.click { it.navigateToEdit(EDIT) }
        }
    }

    private class OnLongClick : LongClickListener<NoteUI> {

        override fun click(item: NoteUI) {

        }
    }

    private val fabClickListener: View.OnClickListener
        get() = View.OnClickListener {
            val note = Note(title = "", content = "", datestamp = "", timestamp = "")
            it.transitionName = note.id.toString()
            it.navigateToEdit(NOT_EDIT)
        }

    private fun View.navigateToEdit(edit: Boolean) {
        val directions = MainFragmentDirections.actionNavHomeToNavEdit(transitionName, edit)
        val extras = FragmentNavigatorExtras(this to transitionName)
        findNavController().navigate(directions, extras)
    }

    private fun progress() = with(binding.SHOWPROGRESS) {
        visibility = if (visibility == View.VISIBLE) View.INVISIBLE
        else View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EDIT: Boolean = true
        private const val NOT_EDIT: Boolean = false
    }
}

