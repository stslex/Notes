package com.stslex93.notes.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.databinding.ItemRecyclerMainBinding
import com.stslex93.notes.utilites.clicker.ItemClickListener

class MainViewHolder(private val binding: ItemRecyclerMainBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {

    private lateinit var clickListener: ItemClickListener
    private lateinit var note: Note

    fun bind(note: Note) {
        this.note = note
        binding.itemRecyclerMainTitle.text = note.title
        binding.itemRecyclerMainContent.text = note.content
        binding.mainItemCard.transitionName = note.id.toString()
    }

    fun setClickListeners(clickListener: ItemClickListener) {
        this.clickListener = clickListener
        binding.root.setOnClickListener(this)
        binding.root.setOnLongClickListener(this)
    }

    override fun onClick(v: View) {
        clickListener.onClick(binding.mainItemCard, note)
    }

    override fun onLongClick(v: View): Boolean {
        clickListener.onClickLong(binding.mainItemCard, note)
        return true
    }
}