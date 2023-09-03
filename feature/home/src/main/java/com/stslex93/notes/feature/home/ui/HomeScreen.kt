package com.stslex93.notes.feature.home.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.stslex93.notes.core.ui.theme.AppDimens
import com.stslex93.notes.core.ui.theme.PreviewTheme
import com.stslex93.notes.feature.home.ui.components.HomeScreenFloatingButton
import com.stslex93.notes.feature.home.ui.components.HomeScreenNotes
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
            HomeSearchNoteField(
                query = query, onClearClick = {
                    sendAction(Action.ClearSelection)
                }, onTextChange = { value ->
                    sendAction(Action.QueryInput(value))
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
fun HomeSearchNoteField(
    query: String,
    onClearClick: () -> Unit,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val hapticFeedback = LocalHapticFeedback.current

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = AppDimens.Padding.small),
        value = query,
        onValueChange = onTextChange,
        trailingIcon = {
            AnimatedVisibility(
                visible = query.isNotEmpty(), enter = scaleIn(
                    spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ), exit = scaleOut(
                    spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
            ) {
                IconButton(onClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClearClick()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Clear, contentDescription = null
                    )
                }
            }
        },
    )
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
                        timestamp = 0
                    )
                }.toList()
            )
        ).collectAsLazyPagingItems()
        HomeScreen(query = "",
            selectedNotes = emptySet<Int>().toImmutableSet(),
            notes = notes,
            sendAction = {})
    }
}