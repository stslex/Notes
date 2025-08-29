package com.stslex93.notes.feature.home.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.stslex93.notes.core.ui.emptyImmutableSet
import com.stslex93.notes.core.ui.theme.AppTheme
import com.stslex93.notes.feature.home.ui.model.Label
import com.stslex93.notes.feature.home.ui.model.Note
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenNotes(
    items: LazyPagingItems<Note>,
    selectedItems: ImmutableSet<Int>,
    onClick: (Int) -> Unit,
    onLingClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
    ) {
        items(
            count = items.itemCount,
            key = items.itemKey { note -> note.id }
        ) { index ->
            items[index]?.let { item ->
                HomeScreenItemNote(
                    modifier = Modifier.animateItem(),
                    isSelected = selectedItems.contains(item.id),
                    item = item,
                    onClick = onClick,
                    onLingClick = onLingClick
                )
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenNotesPreview() {
    AppTheme {
        val labels = Array(10) {
            Label(
                uuid = "uuid$it",
                title = "label$it ".repeat(it.coerceAtLeast(1)),
            )
        }.toSet().toImmutableSet()
        val notes = Array(10) {
            Note(
                id = it,
                title = "title$it ".repeat(it),
                content = "content$it ".repeat(it),
                timestamp = System.currentTimeMillis(),
                labels = labels
            )
        }.toList()

        val items = remember {
            MutableStateFlow(PagingData.from(notes))
        }.collectAsLazyPagingItems()

        HomeScreenNotes(
            items = items,
            selectedItems = emptyImmutableSet(),
            onClick = {},
            onLingClick = {}
        )
    }
}
