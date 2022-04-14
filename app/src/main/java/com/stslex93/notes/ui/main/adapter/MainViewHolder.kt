package com.stslex93.notes.ui.main.adapter

import android.view.View
import com.stslex93.notes.databinding.ItemRecyclerMainBinding
import com.stslex93.notes.ui.core.AbstractViewHolder
import com.stslex93.notes.ui.core.ClickListener
import com.stslex93.notes.ui.core.LongClickListener
import com.stslex93.notes.ui.model.NoteUIModel

class MainViewHolder(
    private val binding: ItemRecyclerMainBinding,
    private val clickListener: ClickListener<NoteUIModel>,
    private val longClickListener: LongClickListener<NoteUIModel>
) : AbstractViewHolder<NoteUIModel>(binding) {

    override fun bind(item: NoteUIModel): Unit = with(item) {
        binding.itemCardView.isChecked = isChecked()
        binding.itemCardView.setOnClickListener(itemClickListener)
        binding.itemCardView.setOnLongClickListener(itemLongCLickListener)
        bindItem()
    }

    private fun NoteUIModel.bindItem(): Unit = with(binding) {
        bind(titleTextView, contentTextView, itemCardView)
    }

    private val NoteUIModel.itemClickListener: View.OnClickListener
        get() = View.OnClickListener { clickListener.onClick(this) }

    private val NoteUIModel.itemLongCLickListener: View.OnLongClickListener
        get() = View.OnLongClickListener {
            longClickListener.click(this)
            true
        }
}