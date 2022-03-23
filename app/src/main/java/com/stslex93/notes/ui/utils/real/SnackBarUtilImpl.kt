package com.stslex93.notes.ui.utils.real

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.stslex93.notes.R
import com.stslex93.notes.ui.utils.interf.SnackBarUtil
import javax.inject.Inject

class SnackBarUtilImpl @Inject constructor() : SnackBarUtil {

    override fun showErrorMessage(view: View, message: String) {
        val theme = view.resources.newTheme()
        val color = view.resources.getColor(R.color.design_default_color_error, theme)
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .apply {
                animationMode = Snackbar.ANIMATION_MODE_SLIDE
                setBackgroundTint(color)
                setAction("Ok") {}
            }.show()
    }
}