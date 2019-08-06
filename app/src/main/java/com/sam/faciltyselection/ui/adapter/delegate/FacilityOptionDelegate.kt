package com.sam.faciltyselection.ui.adapter.delegate

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sam.faciltyselection.R
import com.sam.faciltyselection.databinding.FacilityOptionItemBinding
import com.sam.faciltyselection.repository.model.FacilityOption
import com.sam.faciltyselection.repository.model.FacilitySelectionListItem
import com.sam.faciltyselection.ui.adapter.ViewType
import com.sam.faciltyselection.ui.helper.IconHelper
import com.sam.faciltyselection.ui.listner.ItemClickHandler

class FacilityOptionDelegate(val clickHandler: ItemClickHandler) : AdapterDelegate<FacilitySelectionListItem, RecyclerView.ViewHolder> {

    override fun isItemforViewType(data: FacilitySelectionListItem) = data is FacilityOption

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FacilityOptionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.facility_option_item, parent, false))
    }

    override fun onBindViewHolder(data: FacilitySelectionListItem, holder: RecyclerView.ViewHolder, position: Int) {
        if (data is FacilityOption && holder is FacilityOptionViewHolder){
            holder.binding?.run { 
                this.option = data
                IconHelper.loadCustomIcon(this.icon, data)
                this.facilityCard.setOnClickListener { view ->
                    clickHandler.onClick(position)
                }
            }
        }
    }

    override fun getViewType(data: FacilitySelectionListItem) = ViewType.FACILITY_OPTION.ordinal

    class FacilityOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = DataBindingUtil.bind<FacilityOptionItemBinding>(itemView)
    }
}
