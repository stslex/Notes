package com.stslex93.notes.utilites

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.stslex93.notes.NoteApplication
import com.stslex93.notes.di.component.AppComponent
import java.util.*

val Context.appComponent: AppComponent
    get() = when (this) {
        is NoteApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

fun Fragment.getDrawableIcon(id: Int) =
    ResourcesCompat.getDrawable(resources, id, resources.newTheme())

fun FragmentActivity.hideKeyBoard() {
    val imm: InputMethodManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
}

fun String.lowerContains(text: String) =
    this.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))

inline fun View.showSnackBar(text: String, textAction: String, crossinline function: () -> Unit) {
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT).apply {
        anchorView = this@showSnackBar
        setAction(textAction) { function() }
        show()
    }
}