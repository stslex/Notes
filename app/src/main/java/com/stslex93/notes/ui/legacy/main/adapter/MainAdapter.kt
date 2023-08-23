package com.stslex93.notes.ui.legacy.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.stslex93.notes.databinding.ItemRecyclerMainBinding
import com.stslex93.notes.ui.legacy.core.AbstractPagingAdapter
import com.stslex93.notes.ui.legacy.core.AbstractViewHolder
import com.stslex93.notes.ui.legacy.core.ClickListener
import com.stslex93.notes.ui.legacy.core.LongClickListener
import com.stslex93.notes.ui.legacy.model.NoteUIModel

class MainAdapter(
    private val clickListener: ClickListener<NoteUIModel>,
    private val longClickListener: LongClickListener<NoteUIModel>,
    diffUtil: DiffUtil.ItemCallback<NoteUIModel>
) : AbstractPagingAdapter<NoteUIModel>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder<NoteUIModel> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding, clickListener, longClickListener)
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<NoteUIModel>, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}


