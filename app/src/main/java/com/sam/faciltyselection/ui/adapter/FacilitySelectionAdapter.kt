package com.sam.faciltyselection.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sam.faciltyselection.repository.model.FacilitySelectionListItem
import com.sam.faciltyselection.ui.adapter.delegate.AdapterDelegate
import com.sam.faciltyselection.ui.adapter.delegate.FacilityOptionDelegate
import com.sam.faciltyselection.ui.adapter.delegate.FacilitySectionDelegate
import com.sam.faciltyselection.ui.listner.ItemClickHandler
import javax.inject.Inject

class FacilitySelectionAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemClickHandler{

    private val adapterDelegateManager: AdapterDelegateManager<AdapterDelegate<FacilitySelectionListItem, RecyclerView.ViewHolder>, FacilitySelectionListItem> = AdapterDelegateManager()
    private lateinit var items: List<FacilitySelectionListItem>
    lateinit var clickHandler: ItemClickHandler

    init {
        adapterDelegateManager.addDelegate(ViewType.FACILITY_OPTION.ordinal, FacilityOptionDelegate(this))
        adapterDelegateManager.addDelegate(ViewType.SECTION.ordinal, FacilitySectionDelegate())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return adapterDelegateManager.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        adapterDelegateManager.onBindViewHolder(items.get(position), position, holder)
    }


    override fun getItemViewType(position: Int): Int {
        return adapterDelegateManager.getItemViewType(items.get(position))
    }


    fun update(data: List<FacilitySelectionListItem>) {
        items = data
    }

    override fun onClick(position: Int) = clickHandler.onClick(position)

}