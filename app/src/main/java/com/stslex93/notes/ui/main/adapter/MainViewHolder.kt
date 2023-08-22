package com.stslex93.notes.ui.main.adapter

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

    override fun bind(item: NoteUIModel) {
        val clickListener = itemClickListener(item)
        val longClickListener = itemLongCLickListener(item)

        binding.titleTextView.text = item.title
        binding.titleTextView.setOnClickListener {
            clickListener()
        }

        item.setCardView(binding.itemCardView)
        binding.itemCardView.isChecked = item.isChecked
        binding.itemCardView.transitionName = item.id.toString()
        binding.itemCardView.setOnLongClickListener {
            longClickListener()
            true
        }

        binding.contentTextView.setText(item.content)
        binding.contentTextView.setClickListener {
            clickListener()
        }
    }

    private fun itemClickListener(item: NoteUIModel): () -> Unit = {
        clickListener.onClick(item)
        binding.itemCardView.isChecked = item.isChecked
    }

    private fun itemLongCLickListener(item: NoteUIModel): () -> Unit = {
        longClickListener.click(item)
        binding.itemCardView.isChecked = item.isChecked
    }
}