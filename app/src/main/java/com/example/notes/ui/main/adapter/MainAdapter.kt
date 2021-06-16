package com.example.notes.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ItemRecyclerMainBinding
import com.example.notes.model.base.Note

class MainAdapter(private val clickListener: ItemClickListener) :
    RecyclerView.Adapter<MainViewHolder>() {

    private var notes = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(notes[position], position)
        holder.setClickListeners(clickListener)
    }

    override fun getItemCount(): Int = notes.size

    fun setNotes(notes: List<Note>) {
        this.notes = notes as MutableList<Note>
        notifyDataSetChanged()
    }
}