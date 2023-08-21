package com.stslex93.notes.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stslex93.notes.ui.AppTheme
import com.stslex93.notes.ui.edit.store.EditStore.Action
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditFragment : Fragment() {

    private val args: EditFragmentArgs by navArgs()
    private val viewModel: EditNoteViewModel by viewModel<EditNoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.sendAction(
            Action.Init(
                id = args.id,
                isEdit = args.edit
            )
        )
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
            setContent {
                AppTheme {
                    InitEditScreen()
                }
            }
        }
    }

    @Composable
    private fun InitEditScreen() {
        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_STOP) {
                    viewModel.sendAction(Action.SaveNote)
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        val state by remember { viewModel.state }.collectAsState()

        EditScreen(
            state = state,
            onInputTitle = { viewModel.sendAction(Action.InputTitle(it)) },
            onInputContent = { viewModel.sendAction(Action.InputContent(it)) },
            onBackButtonClicked = remember {
                { findNavController().popBackStack() }
            }
        )
    }
}