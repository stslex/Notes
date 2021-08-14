package com.stslex93.notes.utilites

import android.widget.SearchView

class OnQueryTextListener(
    private val searchView: SearchView,
    private val function: (String) -> Unit
) :
    SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(p0: String): Boolean {
        searchView.clearFocus()
        return false
    }

    override fun onQueryTextChange(p0: String): Boolean {
        function(p0)
        return false
    }
}