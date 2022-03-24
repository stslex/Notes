package com.stslex93.notes.ui.main.adapter

import android.view.View
import com.stslex93.notes.databinding.ItemRecyclerMainBinding
import com.stslex93.notes.ui.core.AbstractViewHolder
import com.stslex93.notes.ui.core.ClickListener
import com.stslex93.notes.ui.core.LongClickListener
import com.stslex93.notes.ui.model.NoteUI

class MainViewHolder(
    private val binding: ItemRecyclerMainBinding,
    private val clickListener: ClickListener<NoteUI>,
    private val longClickListener: LongClickListener<NoteUI>
) : AbstractViewHolder<NoteUI>(binding) {

    override fun bind(item: NoteUI): Unit = with(item) {
        binding.itemCardView.isChecked = isChecked()
        binding.itemCardView.setOnClickListener(itemClickListener)
        binding.itemCardView.setOnLongClickListener(itemLongCLickListener)
        bindItem()
    }

    private fun NoteUI.bindItem(): Unit = with(binding) {
        bind(titleTextView, contentTextView, itemCardView)
    }

    private val NoteUI.itemClickListener: View.OnClickListener
        get() = View.OnClickListener { clickListener.onClick(this) }

    private val NoteUI.itemLongCLickListener: View.OnLongClickListener
        get() = View.OnLongClickListener {
            binding.itemCardView.isChecked = !isChecked()
            longClickListener.click(this)
            true
        }
}