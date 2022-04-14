package com.stslex93.notes.ui.core

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.Dispatchers

abstract class AbstractPagingAdapter<T : Any>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, AbstractViewHolder<T>>(diffCallback, Dispatchers.Main, Dispatchers.IO) {
}