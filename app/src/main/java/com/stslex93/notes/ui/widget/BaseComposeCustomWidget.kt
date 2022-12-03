package com.stslex93.notes.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.stslex93.notes.databinding.ViewComposeCustomBinding
import com.stslex93.notes.ui.AppTheme

abstract class BaseComposeCustomWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @Composable
    abstract fun MainContent(modifier: Modifier)

    init {
        val inflater = LayoutInflater.from(context)
        val binding = ViewComposeCustomBinding.inflate(inflater, this, true)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AppTheme {
                    MainContent(Modifier)
                }
            }
        }
    }
}