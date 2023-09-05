package com.stslex93.notes.feature.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.stslex93.notes.core.ui.theme.AppDimens
import com.stslex93.notes.core.ui.theme.AppTheme
import com.stslex93.notes.feature.home.ui.model.Label
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.toImmutableSet

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreenItemLabels(
    labels: ImmutableSet<Label>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
    ) {
        labels.forEach { label ->
            ElevatedCard(
                modifier = Modifier
                    .padding(
                        end = AppDimens.Padding.smallest,
                        top = AppDimens.Padding.smallest
                    )
                    .wrapContentSize(),
                shape = RoundedCornerShape(AppDimens.Corner.small),
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            vertical = AppDimens.Padding.small,
                            horizontal = AppDimens.Padding.medium
                        )
                        .align(Alignment.CenterHorizontally),
                    text = label.title.take(16),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenItemLabelsPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            val labels = Array(10) {
                Label(
                    uuid = "uuid$it",
                    title = "label$it ".repeat(it.coerceAtLeast(1)),
                )
            }.toSet().toImmutableSet()
            HomeScreenItemLabels(
                labels = labels
            )
        }
    }
}