package com.stslex.notes.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.stslex.notes.databinding.ItemRecyclerMainBinding
import com.stslex.notes.ui.core.AbstractPagingAdapter
import com.stslex.notes.ui.core.AbstractViewHolder
import com.stslex.notes.ui.core.ClickListener
import com.stslex.notes.ui.core.LongClickListener
import com.stslex.notes.ui.model.NoteUIModel

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


