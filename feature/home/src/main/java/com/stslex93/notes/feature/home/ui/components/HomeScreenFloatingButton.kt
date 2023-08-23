package com.stslex93.notes.feature.home.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import com.stslex93.notes.core.ui.theme.AppDimens

@Composable
fun HomeScreenFloatingButton(
    isSelectingAction: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val hapticFeedback = LocalHapticFeedback.current

    val containerColor by animateColorAsState(
        targetValue = if (isSelectingAction) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.primaryContainer
        },
        animationSpec = tween(600, delayMillis = 90),
        label = "containerColor animation"
    )

    val contentColor by animateColorAsState(
        targetValue = if (isSelectingAction) {
            MaterialTheme.colorScheme.onSecondaryContainer
        } else {
            MaterialTheme.colorScheme.onPrimaryContainer
        },
        animationSpec = tween(600, delayMillis = 90),
        label = "containerColor animation"
    )

    FloatingActionButton(
        modifier = modifier
            .padding(AppDimens.Padding.big),
        onClick = {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            onClick()
        },
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        AnimatedContent(
            targetState = isSelectingAction,
            transitionSpec = {
                (fadeIn(animationSpec = tween(600, delayMillis = 90)) +
                        scaleIn(initialScale = 0.92f, animationSpec = tween(600, delayMillis = 90)))
                    .togetherWith(fadeOut(animationSpec = tween(90)))
            },
            label = "home floating button animation",
        ) { isDeleting ->
            Icon(
                imageVector = if (isDeleting) {
                    Icons.Default.Add
                } else {
                    Icons.Default.Delete
                },
                contentDescription = null
            )
        }
    }
}