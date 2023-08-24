package com.stslex93.notes.feature.home.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.stslex93.notes.feature.home.ui.model.Note
import kotlinx.collections.immutable.ImmutableSet

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenNotes(
    items: LazyPagingItems<Note>,
    selectedItems: ImmutableSet<Int>,
    onClick: (Int) -> Unit,
    onLingClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        items(
            count = items.itemCount,
            key = items.itemKey { note -> note.id }
        ) { index ->
            items[index]?.let { item ->
                HomeScreenItemNote(
                    modifier = Modifier
                        .animateItemPlacement(tween(600)),
                    isSelected = selectedItems.contains(item.id),
                    item = item,
                    onClick = onClick,
                    onLingClick = onLingClick
                )
            }
        }
    }
}
