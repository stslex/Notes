package com.stslex93.notes.ui.legacy.widget

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

class ComposeCustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseComposeCustomWidget(context, attrs, defStyleAttr) {

    private val textState = mutableStateOf(String())
    private var onClick: () -> Unit = {}

    @Composable
    override fun MainContent(modifier: Modifier) {
        Text(
            modifier = modifier.clickable(onClick = onClick),
            text = textState.value,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

    fun setText(text: String) {
        textState.value = text
    }

    fun setClickListener(
        onClick: () -> Unit
    ) {
        this.onClick = onClick
    }
}