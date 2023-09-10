package com.stslex93.notes.feature.home.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.stslex93.notes.core.ui.theme.AppDimens
import com.stslex93.notes.core.ui.theme.AppTheme
import com.stslex93.notes.feature.home.R

@Composable
fun HomeScreenToolbar(
    isSelectingMode: Boolean,
    query: String,
    onClearClick: () -> Unit,
    onLabelCreateClick: () -> Unit,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = isSelectingMode,
        label = "animate toolbar"
    ) { isOptionsToolbar ->
        if (isOptionsToolbar) {
            HomeScreenOptionsToolbar(
                onLabelCreateClick = onLabelCreateClick
            )
        } else {
            HomeScreenSearchToolbar(
                query = query,
                onClearClick = onClearClick,
                onTextChange = onTextChange
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenOptionsToolbar(
    onLabelCreateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .shadow(AppDimens.Elevation.medium),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        title = {},
        actions = {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                onClick = onLabelCreateClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_label_24),
                    contentDescription = "create label",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

@Composable
fun HomeScreenSearchToolbar(
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
                IconButton(
                    onClick = {
                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                        onClearClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Clear, contentDescription = null
                    )
                }
            }
        },
    )
}

@Composable
@Preview
fun HomeScreenToolbarPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            HomeScreenToolbar(
                modifier = Modifier.align(Alignment.TopCenter),
                isSelectingMode = true,
                query = "test",
                onClearClick = { /*TODO*/ },
                onTextChange = {},
                onLabelCreateClick = {}
            )
        }
    }
}