package com.stslex.notes.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stslex.notes.ui.model.NoteUIModel

class NotesDiffUtilCallback(
    private val oldList: List<NoteUIModel>,
    private val newList: List<NoteUIModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id() == newList[newItemPosition].id()
}