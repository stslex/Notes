package com.stslex93.notes.ui.main.utils

import androidx.appcompat.widget.SearchView
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class QueryTextListener @AssistedInject constructor(
    @Assisted private val textChange: (String) -> Unit
) : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String): Boolean {
        textChange.invoke(query)
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        textChange.invoke(newText)
        return false
    }

    @AssistedFactory
    fun interface Factory {
        fun create(@Assisted textChange: (String) -> Unit): QueryTextListener
    }
}