package com.stslex.notes.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stslex.notes.databinding.ItemRecyclerMainBinding
import com.stslex.notes.ui.core.AbstractViewHolder
import com.stslex.notes.ui.core.ClickListener
import com.stslex.notes.ui.core.LongClickListener
import com.stslex.notes.ui.model.NoteUIModel

class MainAdapter(
    private val clickListener: ClickListener<NoteUIModel>,
    private val longClickListener: LongClickListener<NoteUIModel>
) : RecyclerView.Adapter<AbstractViewHolder<NoteUIModel>>() {

    private val _items: MutableList<NoteUIModel> = mutableListOf()
    private val items: List<NoteUIModel> get() = _items

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder<NoteUIModel> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding, clickListener, longClickListener)
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<NoteUIModel>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<NoteUIModel>) {
        val addedItems = sort(newItems)
        val diffUtil = NotesDiffUtilCallback(items, addedItems)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        _items.clear()
        _items.addAll(addedItems)
        diffResult.dispatchUpdatesTo(this)

    }

    private fun sort(newItems: List<NoteUIModel>): List<NoteUIModel> =
        mutableListOf<NoteUIModel>().apply {
            addAll(newItems)
            sortBy(sortedItems)
            reverse()
        }

    private val sortedItems: (NoteUIModel) -> Long
        get() = NoteUIModel::timestamp
}


