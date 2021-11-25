package com.stslex93.notes.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.stslex93.notes.ui.main.NoteUI

class NotesDiffUtilCallback(
    private val oldList: List<NoteUI>,
    private val newList: List<NoteUI>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].getId() == newList[newItemPosition].getId()
}