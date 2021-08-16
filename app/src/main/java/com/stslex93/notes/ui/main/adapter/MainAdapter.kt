package com.stslex93.notes.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.databinding.ItemRecyclerMainBinding
import com.stslex93.notes.utilites.clicker.ItemClickListener

class MainAdapter(private val clickListener: ItemClickListener) :
    RecyclerView.Adapter<MainViewHolder>() {

    private var listOfNotes = emptyList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listOfNotes[position])
        holder.setClickListeners(clickListener)
    }

    override fun getItemCount(): Int = listOfNotes.size

    fun setNotes(notes: List<Note>) {
        val position = itemCount
        listOfNotes = notes
        if (notes.isNotEmpty()) {
            when {
                position == 0 -> {
                    notifyItemRangeInserted(0, listOfNotes.size)
                }
                position < notes.size -> {
                    notifyItemRangeChanged(0, listOfNotes.size)
                }
                position > notes.size -> {
                    notifyItemRangeChanged(0, position)
                }
                position == notes.size -> {
                    notifyItemRangeChanged(0, position)
                }
            }
        } else {
            notifyItemRangeRemoved(0, position)
        }

    }

}


