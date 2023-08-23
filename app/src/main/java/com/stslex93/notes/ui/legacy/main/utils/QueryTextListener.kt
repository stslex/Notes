package com.stslex93.notes.ui.legacy.main.utils

import androidx.appcompat.widget.SearchView

class QueryTextListener(
    private val textChange: (String) -> Unit
) : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String): Boolean {
        textChange.invoke(query)
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        textChange.invoke(newText)
        return false
    }
}