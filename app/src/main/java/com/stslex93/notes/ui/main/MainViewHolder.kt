package com.stslex93.notes.ui.main

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stslex93.notes.databinding.ItemRecyclerMainBinding
import com.stslex93.notes.ui.core.ClickListener
import com.stslex93.notes.ui.core.LongClickListener

abstract class MainViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    abstract fun bind(item: NoteUI)

    class Base(
        private val binding: ItemRecyclerMainBinding,
        private val clickListener: ClickListener<NoteUI>,
        private val longClickListener: LongClickListener<NoteUI>
    ) : MainViewHolder(binding.root) {

        override fun bind(item: NoteUI) {
            with(binding) {
                Log.i("TAG", item.toString())
                item.bind(
                    titleTextView = titleTextView,
                    contentTextView = contentTextView,
                    itemCardView = itemCardView
                )
                itemCardView.setOnClickListener {
                    clickListener.onClick(item)
                }
                itemCardView.setOnLongClickListener {
                    longClickListener.click(item)
                    true
                }
            }
        }
    }

}