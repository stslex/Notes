package com.stslex93.notes.feature.edit_label.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.stslex93.notes.core.ui.emptyImmutableSet
import com.stslex93.notes.core.ui.theme.AppDimens
import com.stslex93.notes.core.ui.theme.AppTheme
import com.stslex93.notes.feature.edit_label.ui.model.Label
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.Action
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore.State
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditLabelScreen(
    state: State,
    processAction: (Action) -> Unit,
    modifier: Modifier = Modifier
) {
    val labels = remember {
        state.labels()
    }.collectAsLazyPagingItems()

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background)
            .padding(AppDimens.Padding.medium)
    ) {
        EditLabelToolbar(
            query = state.query,
            onQueryChange = { value ->
                processAction(Action.QueryInput(value))
            },
            onBackPressed = {
                processAction(Action.BackPressed)
            }
        )
        Spacer(modifier = Modifier.height(AppDimens.Padding.normal))

        AnimatedVisibility(visible = state.query.isNotBlank()) {
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppDimens.Padding.medium),
                onClick = {
                    processAction(Action.AddLabelClicked)
                },
                contentPadding = PaddingValues(AppDimens.Padding.normal)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add label"
                )
                Spacer(modifier = Modifier.width(AppDimens.Padding.small))
                Text(
                    text = "Save: ${state.query}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(AppDimens.Padding.medium)
        ) {
            items(
                count = labels.itemCount,
                key = labels.itemKey {
                    it.uuid
                },
            ) { index ->
                labels[index]?.let { item ->
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = AppDimens.Padding.medium
                            ),
                        onClick = {
                            processAction(Action.OnLabelSelected(item.uuid))
                        }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(AppDimens.Padding.medium),
                            text = item.title,
                            maxLines = 1,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EditLabelToolbar(
    query: String,
    onQueryChange: (String) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppDimens.Padding.medium)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        IconButton(
            onClick = onBackPressed
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "back",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.width(AppDimens.Padding.medium))
        TextField(
            modifier = Modifier.weight(1f),
            value = query,
            onValueChange = { value ->
                if (query != value) {
                    onQueryChange(value)
                }
            },
            maxLines = 1,
            textStyle = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
@Preview
fun EditLabelScreenPreview() {
    AppTheme {
        val labels = Array(10) {
            Label(
                uuid = it.toString(),
                title = "label $it"
            )
        }.toList()
        val items = MutableStateFlow(PagingData.from(labels))
        EditLabelScreen(
            state = State(
                notesIds = emptyImmutableSet(),
                query = "dfdfdfdfdf",
                labels = { items }
            ),
            processAction = {}
        )
    }
}