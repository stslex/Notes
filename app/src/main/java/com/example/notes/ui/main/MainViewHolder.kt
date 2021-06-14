package com.example.notes.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ItemRecyclerMainBinding
import com.example.notes.model.base.Note

class MainViewHolder(private val binding: ItemRecyclerMainBinding):
    RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {

    private lateinit var clickListener: ItemClickListener
    private lateinit var note:Note
    private lateinit var key:String

    fun bind(note: Note, clickListener: ItemClickListener, position: Int) {
        this.note = note
        this.clickListener = clickListener

        binding.itemRecyclerMainTitle.text = note.title
        binding.itemRecyclerMainContent.text = note.content

        key = "card$position"
        binding.mainItemCard.transitionName = key

        binding.root.setOnClickListener(this)
        binding.root.setOnLongClickListener(this)
    }

    override fun onClick(v: View?) {
        clickListener.clickListener(binding.mainItemCard, note, key)
    }

    override fun onLongClick(v: View?): Boolean {
        clickListener.onLongClickListener(binding.mainItemCard, note)
        return true
    }
}