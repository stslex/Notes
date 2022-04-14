package com.stslex93.notes.ui.main.utils

import androidx.recyclerview.widget.DiffUtil
import com.stslex93.notes.ui.model.NoteUIModel

class NotesDiffItemCallback : DiffUtil.ItemCallback<NoteUIModel>() {

    override fun areItemsTheSame(oldItem: NoteUIModel, newItem: NoteUIModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: NoteUIModel, newItem: NoteUIModel): Boolean =
        oldItem.id() == newItem.id()
}