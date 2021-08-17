package com.stslex93.notes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.card.MaterialCardView

open class MainNoteClick {

    private val _checkNotes = mutableListOf<String>()
    val checkNotes: List<String> get() = _checkNotes

    private val _checkCards = mutableListOf<MaterialCardView>()
    val checkCards: List<MaterialCardView> get() = _checkCards

    private val _isChecking: MutableLiveData<Boolean> = MutableLiveData(false)
    val isChecking: LiveData<Boolean> get() = _isChecking

    fun checkCardClick(card: MaterialCardView) {
        if (card.isChecked) {
            card.cardCheckRemove()
        } else {
            card.cardCheckAdd()
        }
    }

    private fun MaterialCardView.cardCheckAdd() {
        isChecked = true
        _isChecking.value = true
        _checkCards.add(this)
        _checkNotes.add(transitionName)
    }

    private fun MaterialCardView.cardCheckRemove() {
        isChecked = false
        _checkCards.remove(this)
        _checkNotes.remove(transitionName)
        if (_checkCards.isEmpty()) {
            _isChecking.value = false
        }
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