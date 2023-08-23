package com.stslex93.notes.ui.legacy.utils.snackbar

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.stslex93.notes.R

class SnackBarUtilImpl : SnackBarUtil {

    override fun showErrorMessage(view: View, message: String) {
        val color = Color.RED // TODO replace
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .apply {
                animationMode = Snackbar.ANIMATION_MODE_SLIDE
                setBackgroundTint(color)
                setAction("Ok") {}
            }.show()
    }

    override fun showSuccess(view: View, action: () -> Unit) {
        val theme = view.resources.newTheme()
        val color = view.resources.getColor(R.color.success, theme)
        Snackbar.make(view, "Notes Deleted", Snackbar.LENGTH_SHORT).apply {
            animationMode = Snackbar.ANIMATION_MODE_SLIDE
            setBackgroundTint(color)
            setActionTextColor(Color.BLACK)
            setTextColor(Color.BLACK)
            setAction("CANCEL") {
                action()
            }
        }.show()
    }
}