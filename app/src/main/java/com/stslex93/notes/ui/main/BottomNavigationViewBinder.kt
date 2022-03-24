package com.stslex93.notes.ui.main

import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.core.view.forEach
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.math.MathUtils
import com.google.android.material.navigation.NavigationView
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class BottomNavigationViewBinder @AssistedInject constructor(
    @Assisted("navigationView") private val navigationView: NavigationView,
    @Assisted("bottomAppBar") private val bottomAppBar: BottomAppBar,
    @Assisted("scrim") private val scrim: FrameLayout,
    @Assisted("fab") private val fab: FloatingActionButton
) {
    fun bind() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomAppBar.setNavigationOnClickListener(bottomAppBarClickListener)
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener)
        scrim.setOnClickListener(scrimClickListener)
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallBack)
    }

    private val bottomSheetBehavior by lazy {
        BottomSheetBehavior.from(navigationView)
    }

    private val bottomAppBarClickListener: View.OnClickListener = View.OnClickListener {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            fab.show()
            scrim.visibility = View.GONE
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            fab.hide()
            scrim.visibility = View.VISIBLE
        }
    }

    private val navigationItemSelectedListener: NavigationView.OnNavigationItemSelectedListener =
        NavigationView.OnNavigationItemSelectedListener { menuItem ->
            navigationView.menu.forEach { it.isChecked = false }
            menuItem.isChecked = true
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            true
        }

    private val scrimClickListener: View.OnClickListener = View.OnClickListener {
        fab.show()
        it.visibility = View.GONE
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private val bottomSheetCallBack: BottomSheetBehavior.BottomSheetCallback =
        object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    fab.show()
                    scrim.visibility = View.GONE
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val baseColor = Color.BLACK
                val baseAlpha = 0.6f
                val offset = (slideOffset - (-1f)) / (1f - (-1f)) * (1f - 0f) + 0f
                val alpha = MathUtils.lerp(0f, 255f, offset * baseAlpha).toInt()
                val color = Color.argb(alpha, baseColor.red, baseColor.green, baseColor.blue)
                scrim.setBackgroundColor(color)
            }
        }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("navigationView") navigationView: NavigationView,
            @Assisted("bottomAppBar") bottomAppBar: BottomAppBar,
            @Assisted("scrim") scrim: FrameLayout,
            @Assisted("fab") fab: FloatingActionButton
        ): BottomNavigationViewBinder
    }
}
