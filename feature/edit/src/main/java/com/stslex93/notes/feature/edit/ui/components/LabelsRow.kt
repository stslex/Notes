package com.stslex93.notes.feature.edit.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.stslex93.notes.core.ui.theme.AppDimens
import com.stslex93.notes.core.ui.theme.AppTheme
import com.stslex93.notes.feature.edit.ui.model.Label
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.toImmutableSet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelsHeader(
    labels: ImmutableSet<Label>,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    if (labels.isNotEmpty()) {
        OutlinedCard(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppDimens.Padding.normal
                ),
            onClick = {
                isExpanded = isExpanded.not()
            }
        ) {
            Text(
                modifier = Modifier
                    .padding(AppDimens.Padding.medium),
                text = "Labels",
                style = MaterialTheme.typography.bodyMedium
            )
            AnimatedVisibility(
                visible = isExpanded
            ) {
                HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                LabelsRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppDimens.Padding.medium),
                    labels = labels
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LabelsRow(
    labels: ImmutableSet<Label>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
    ) {
        labels.forEach { label ->
            ElevatedCard(
                modifier = Modifier
                    .padding(AppDimens.Padding.small)
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
fun LabelsRowPreview() {
    AppTheme {
        val labels = Array(10) { index ->
            Label(
                uuid = "uuid $index",
                title = "labelNum$index"
            )
        }.toList().toImmutableSet()

        LabelsRow(
            labels = labels
        )
    }
}