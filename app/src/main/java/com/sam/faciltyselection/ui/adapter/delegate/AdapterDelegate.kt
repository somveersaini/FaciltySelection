package com.sam.faciltyselection.ui.adapter.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface AdapterDelegate<T, V : RecyclerView.ViewHolder> {

    fun isItemforViewType(data: T): Boolean

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V

    fun onBindViewHolder(data: T, holder: V, position: Int)

    fun getViewType(data: T): Int
}