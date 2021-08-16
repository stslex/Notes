package com.stslex93.notes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.card.MaterialCardView

open class MainNoteClick {
    private val _checkNotes = mutableListOf<String>()
    val checkNotes: List<String> get() = _checkNotes

    private val _checkCards = mutableListOf<MaterialCardView>()
    val checkCards: List<MaterialCardView> get() = _checkCards

    private val _isChecking = MutableLiveData<Boolean>()
    val isChecking: LiveData<Boolean> get() = _isChecking

    fun cardCheckRemove(
        card: MaterialCardView,
        id: String
    ) {
        card.isChecked = false
        _checkCards.remove(card)
        _checkNotes.remove(id)
        if (_checkCards.isEmpty()) {
            _isChecking.value = false
        }
    }

    fun cardCheckAdd(card: MaterialCardView, id: String) {
        card.isChecked = true
        _isChecking.value = true
        _checkCards.add(card)
        _checkNotes.add(id)
    }

    fun deleteAll() {
        _checkCards.clear()
        _checkNotes.clear()
    }

    fun clear() {
        _checkCards.forEach {
            it.isChecked = false
        }
        _isChecking.value = false
    }


}