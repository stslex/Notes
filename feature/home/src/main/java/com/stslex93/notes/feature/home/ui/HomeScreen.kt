package com.stslex93.notes.feature.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.stslex93.notes.core.ui.emptyImmutableSet
import com.stslex93.notes.core.ui.theme.PreviewTheme
import com.stslex93.notes.feature.home.ui.components.HomeScreenFloatingButton
import com.stslex93.notes.feature.home.ui.components.HomeScreenNotes
import com.stslex93.notes.feature.home.ui.components.HomeScreenToolbar
import com.stslex93.notes.feature.home.ui.model.Note
import com.stslex93.notes.feature.home.ui.store.HomeStore.Action
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HomeScreen(
    query: String,
    selectedNotes: ImmutableSet<Int>,
    notes: LazyPagingItems<Note>,
    sendAction: (Action) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeScreenToolbar(
                isSelectingMode = selectedNotes.isNotEmpty(),
                query = query,
                onClearClick = {
                    sendAction(Action.ClearSelection)
                }, onTextChange = { value ->
                    sendAction(Action.QueryInput(value))
                },
                onLabelCreateClick = {
                    sendAction(Action.OnLabelEditClick)
                }
            )
            HomeScreenNotes(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                items = notes,
                selectedItems = selectedNotes,
                onClick = { id ->
                    sendAction(Action.OnNoteClick(id))
                },
                onLingClick = { id ->
                    sendAction(Action.OnNoteLongClick(id))
                }
            )
        }

        HomeScreenFloatingButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            isSelectingAction = selectedNotes.isEmpty()
        ) {
            sendAction(Action.OnNoteFloatingButtonClick)
        }
    }
}

@Composable
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
fun HomeScreenPreview() {
    PreviewTheme {
        val notes = MutableStateFlow(
            PagingData.from(
                Array(10) { index ->
                    Note(
                        id = index,
                        title = "title $index",
                        content = "content: $index",
                        timestamp = 0,
                        labels = emptyImmutableSet()
                    )
                }.toList()
            )
        ).collectAsLazyPagingItems()
        HomeScreen(
            query = "",
            selectedNotes = setOf<Int>(1).toImmutableSet(),
            notes = notes,
            sendAction = {}
        )
    }
}