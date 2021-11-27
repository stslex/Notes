package com.stslex93.notes.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stslex93.notes.databinding.ItemRecyclerMainBinding
import com.stslex93.notes.ui.core.ClickListener
import com.stslex93.notes.ui.core.LongClickListener
import com.stslex93.notes.ui.model.NoteUI

abstract class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: NoteUI)

    class Base(
        private val binding: ItemRecyclerMainBinding,
        private val clickListener: ClickListener<NoteUI>,
        private val longClickListener: LongClickListener<NoteUI>
    ) : MainViewHolder(binding.root) {

        override fun bind(item: NoteUI): Unit = with(item) {
            binding.itemCardView.isChecked = isChecked()
            binding.itemCardView.setOnClickListener(itemClickListener)
            binding.itemCardView.setOnLongClickListener(itemLongCLickListener)
            item.bindItem()
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
}