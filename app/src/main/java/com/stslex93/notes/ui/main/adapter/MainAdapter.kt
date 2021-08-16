package com.stslex93.notes.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.databinding.ItemRecyclerMainBinding
import com.stslex93.notes.utilites.clicker.ItemClickListener

class MainAdapter(private val clickListener: ItemClickListener) :
    RecyclerView.Adapter<MainViewHolder>() {

    private var notes = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(notes[position])
        holder.setClickListeners(clickListener)
    }

    override fun getItemCount(): Int = notes.size

    fun setNotes(notes: List<Note>) {
        val startPosition = this.itemCount
        this.notes = notes as MutableList<Note>
        if (startPosition > this.itemCount) {
            notifyItemRangeRemoved(this.itemCount, startPosition)
        } else {
            notifyItemRangeInserted(startPosition, this.itemCount)
        }
    }

}


