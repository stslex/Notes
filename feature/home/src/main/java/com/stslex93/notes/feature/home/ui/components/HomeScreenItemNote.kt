package com.stslex93.notes.feature.home.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import com.stslex93.notes.core.ui.theme.AppDimens
import com.stslex93.notes.feature.home.ui.model.Note

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenItemNote(
    isSelected: Boolean,
    item: Note,
    onClick: (Int) -> Unit,
    onLingClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val hapticFeedback = LocalHapticFeedback.current

    val colorBackground by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        },
        animationSpec = tween(300),
        label = "note color container animation"
    )

    val colorText by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.onPrimaryContainer
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        animationSpec = tween(300),
        label = "note color text animation"
    )

    val padding by animateDpAsState(
        targetValue = if (isSelected) {
            AppDimens.Padding.big
        } else {
            AppDimens.Padding.normal
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "note padding animation"
    )

    val maxLines by animateIntAsState(
        targetValue = if (isSelected) 5 else 3,
        animationSpec = tween(300),
        label = "animate note max lines"
    )

    Column(
        modifier = modifier
            .padding(AppDimens.Padding.normal)
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = AppDimens.Padding.small,
                shape = RoundedCornerShape(AppDimens.Corner.normal)
            )
            .background(colorBackground)
            .combinedClickable(
                onClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    onClick(item.id)
                },
                onLongClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    onLingClick(item.id)
                }
            )
            .padding(padding)

    ) {
        Text(
            text = item.title,
            color = colorText,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
        )
        Spacer(modifier = Modifier.height(AppDimens.Padding.small))
        Text(
            modifier = Modifier.animateContentSize(tween(300)),
            text = item.content,
            style = MaterialTheme.typography.bodyMedium,
            color = colorText,
            maxLines = maxLines
        )
    }
}