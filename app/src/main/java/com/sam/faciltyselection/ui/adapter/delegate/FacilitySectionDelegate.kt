package com.sam.faciltyselection.ui.adapter.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sam.faciltyselection.R
import com.sam.faciltyselection.databinding.SectionItemBinding
import com.sam.faciltyselection.repository.model.FacilitySelectionListItem
import com.sam.faciltyselection.repository.model.FacilitySection
import com.sam.faciltyselection.ui.adapter.ViewType

class FacilitySectionDelegate : AdapterDelegate<FacilitySelectionListItem, RecyclerView.ViewHolder> {

    override fun isItemforViewType(data: FacilitySelectionListItem) = data is FacilitySection

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.section_item, parent, false))
    }

    override fun onBindViewHolder(data: FacilitySelectionListItem, holder: RecyclerView.ViewHolder, position: Int) {
        if (data is FacilitySection && holder is SectionViewHolder){
            holder.binding?.section = data
        }
    }

    override fun getViewType(data: FacilitySelectionListItem) = ViewType.SECTION.ordinal

    class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = DataBindingUtil.bind<SectionItemBinding>(itemView)
    }
}
