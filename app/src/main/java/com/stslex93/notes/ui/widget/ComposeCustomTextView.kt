package com.stslex93.notes.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

class ComposeCustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseComposeCustomWidget(context, attrs, defStyleAttr) {

    private val textState = mutableStateOf(String())

    @Composable
    override fun MainContent(modifier: Modifier) {
        var isExpand by remember {
            mutableStateOf(false)
        }
        val maxLines by animateIntAsState(
            targetValue = if (isExpand) 20 else 2,
            animationSpec = tween(
                durationMillis = 300,
                delayMillis = 50,
                easing = LinearOutSlowInEasing
            )
        )
        Text(
            modifier = modifier.clickable {
                isExpand = isExpand.not()
            },
            text = textState.value,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

    fun setText(text: String) {
        textState.value = text
    }
}