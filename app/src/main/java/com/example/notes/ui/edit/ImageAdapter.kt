package com.example.notes.ui.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ItemRecyclerImageBinding
import com.google.android.material.imageview.ShapeableImageView

class ImageAdapter:RecyclerView.Adapter<ImageViewHolder>() {

    private var itemsList = mutableListOf<ShapeableImageView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = itemsList.size
}