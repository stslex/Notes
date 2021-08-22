package com.stslex93.notes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.card.MaterialCardView

class MainNoteClick {

    private val _checkNotesId = mutableListOf<String>()
    private val _checkCards = mutableListOf<MaterialCardView>()
    private val _isChecking: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkNotesId: List<String> get() = _checkNotesId
    val isChecking: LiveData<Boolean> get() = _isChecking

    fun MaterialCardView.checkCardClick() {
        if (isChecked) {
            cardCheckRemove()
        } else {
            cardCheckAdd()
        }
    }

    private fun MaterialCardView.cardCheckAdd() {
        isChecked = true
        _isChecking.value = true
        _checkCards.add(this)
        _checkNotesId.add(transitionName)
    }

    private fun MaterialCardView.cardCheckRemove() {
        isChecked = false
        _checkCards.remove(this)
        _checkNotesId.remove(transitionName)
        if (_checkCards.isEmpty()) {
            _isChecking.value = false
        }
    }

    fun deleteAll() {
        _checkCards.clear()
        _checkNotesId.clear()
    }

    fun clearCheck() {
        _checkCards.forEach { it.isChecked = false }
        _isChecking.value = false
    }
}