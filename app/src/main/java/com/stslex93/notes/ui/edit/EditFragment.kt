package com.stslex93.notes.ui.edit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stslex93.notes.appComponent
import com.stslex93.notes.ui.AppTheme
import com.stslex93.notes.ui.utils.snackbar.SnackBarUtil
import com.stslex93.notes.ui.utils.time.TimeUtil
import javax.inject.Inject

class EditFragment : Fragment() {

    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var timeUtil: TimeUtil
    private lateinit var snackBarUtil: SnackBarUtil
    private val args: EditFragmentArgs by navArgs()
    private val viewModel: EditNoteViewModel by viewModels { viewModelFactory }

    @Inject
    fun injection(
        viewModelFactory: ViewModelProvider.Factory,
        timeUtil: TimeUtil,
        snackBarUtil: SnackBarUtil
    ) {
        this.viewModelFactory = viewModelFactory
        this.timeUtil = timeUtil
        this.snackBarUtil = snackBarUtil
    }

    override fun onAttach(context: Context) {
        requireActivity().appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
        setContent {
            AppTheme {
                EditScreen(
                    noteId = args.id,
                    getNoteById = {
                        viewModel.getNoteById(id)
                        viewModel.note
                    },
                    insertNote = viewModel::insertNote,
                    onBackButtonClicked = remember {
                        { findNavController().popBackStack() }
                    }
                )
            }
        }
    }
}