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

    fun setNotes(notes: List<Note>, search: Boolean = false) {
        val startPosition = itemCount
        listOfNotes = notes
        when {
            search -> {
                notifyItemRangeChanged(0, itemCount)
                notifyItemRangeRemoved(itemCount, startPosition - itemCount)
            }
            startPosition > itemCount -> {
                notifyItemRangeRemoved(itemCount, startPosition)
            }
            startPosition < itemCount -> {
                notifyItemRangeInserted(startPosition, itemCount)
            }
        }

    }

}


