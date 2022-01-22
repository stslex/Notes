package com.stslex93.notes.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stslex93.notes.databinding.ItemRecyclerMainBinding
import com.stslex93.notes.ui.core.ClickListener
import com.stslex93.notes.ui.core.LongClickListener
import com.stslex93.notes.ui.model.NoteUI

class MainAdapter(
    private val clickListener: ClickListener<NoteUI>,
    private val longClickListener: LongClickListener<NoteUI>
) : RecyclerView.Adapter<MainViewHolder>() {

    private val _items: MutableList<NoteUI> = mutableListOf()
    private val items: List<NoteUI> get() = _items
    private var allSelected: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerMainBinding.inflate(inflater, parent, false)
        return MainViewHolder.Base(binding, clickListener, longClickListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun selectAll(): List<NoteUI> {
        allSelected = !allSelected
        _items.map(::changeItemChecked)
        notifyItemRangeChanged(0, items.size)
        return if (allSelected) items else emptyList()
    }

    private fun changeItemChecked(item: NoteUI) = item.apply { setChecked(allSelected) }

    fun clearSelection() {
        allSelected = false
        notifyItemRangeChanged(0, items.size)
    }

    fun setItems(newItems: List<NoteUI>) {
        val addedItems = sort(newItems)
        val diffUtil = NotesDiffUtilCallback(items, addedItems)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        _items.clear()
        _items.addAll(addedItems)
        diffResult.dispatchUpdatesTo(this)

    }

    private fun sort(newItems: List<NoteUI>): List<NoteUI> = mutableListOf<NoteUI>().apply {
        addAll(newItems)
        sortBy(sortedItems)
        reverse()
    }

    private val sortedItems: (NoteUI) -> Long
        get() = NoteUI::getTimestamp
}


