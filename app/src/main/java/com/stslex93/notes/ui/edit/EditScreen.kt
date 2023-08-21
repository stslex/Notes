package com.stslex93.notes.ui.edit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.stslex93.notes.R
import com.stslex93.notes.ui.model.NoteUIModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EditScreen(
    noteId: Int,
    getNoteById: (Int) -> StateFlow<NoteUIModel>,
    insertNote: (NoteUIModel) -> Unit,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val note by remember {
        getNoteById(noteId)
    }.collectAsState()

    var titleInputValue by remember {
        mutableStateOf("")
    }

    var contentInputValue by remember {
        mutableStateOf("")
    }

    fun getSavingNote(): NoteUIModel = NoteUIModel(
        id = noteId,
        title = titleInputValue,
        content = contentInputValue,
        timestamp = System.currentTimeMillis()
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (
                event == Lifecycle.Event.ON_STOP &&
                (titleInputValue.isNotEmpty() || contentInputValue.isNotEmpty())
            ) {
                insertNote(getSavingNote())
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        TextField(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = titleInputValue,
            onValueChange = { value ->
                if (value != titleInputValue) {
                    titleInputValue = value
                }
            },
            maxLines = 1,
            label = {
                Text(text = stringResource(id = R.string.title_hint))
            }
        )

        TextField(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            value = contentInputValue,
            onValueChange = { value ->
                if (value != contentInputValue) {
                    contentInputValue = value
                }
            },
            label = {
                Text(text = stringResource(id = R.string.content_hint))
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.label_edit) + ":${note.timeString}"
            )

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(16.dp),
                onClick = onBackButtonClicked
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "return"
                )
            }
        }
    }
}