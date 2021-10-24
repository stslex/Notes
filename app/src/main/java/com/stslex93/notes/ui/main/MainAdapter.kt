package com.stslex93.notes.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stslex93.notes.databinding.ItemRecyclerMainBinding
import com.stslex93.notes.ui.core.ClickListener
import com.stslex93.notes.ui.core.LongClickListener

class MainAdapter(
    private val clickListener: ClickListener<NoteUI>,
    private val longClickListener: LongClickListener<NoteUI>
) : RecyclerView.Adapter<MainViewHolder>() {

    private var listOfNotes = mutableListOf<NoteUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerMainBinding.inflate(inflater, parent, false)
        return MainViewHolder.Base(binding, clickListener, longClickListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listOfNotes[position])
    }

    override fun getItemCount(): Int = listOfNotes.size

    fun setNotes(newNotesList: List<NoteUI>) {
        val diffUtil = NotesDiffUtilCallback(oldList = listOfNotes, newList = newNotesList)
        val calculate = DiffUtil.calculateDiff(diffUtil)
        Log.i("TAG", newNotesList.toString())
        listOfNotes.clear()
        listOfNotes.addAll(newNotesList)
        calculate.dispatchUpdatesTo(this)
    }
}


