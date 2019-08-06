package com.sam.faciltyselection.ui.helper

import android.widget.ImageView
import com.sam.faciltyselection.R
import com.sam.faciltyselection.repository.model.FacilityOption

object IconHelper {
    val APARTMENT = "apartment"
    val CONDO = "condo"
    val BOAT = "boat"
    val GARDEN = "garden"
    val GARAGE = "garage"
    val NOROOM = "no-room"
    val ROOMS = "rooms"
    val SWIMMING = "swimming"
    val LAND = "land"

    @JvmStatic
    fun loadCustomIcon(imageView: ImageView, data: FacilityOption){
        when(data.option_icon){
            APARTMENT -> imageView.setImageResource(R.mipmap.apartment)
            CONDO -> imageView.setImageResource(R.mipmap.condo)
            BOAT -> imageView.setImageResource(R.mipmap.boat)
            GARAGE -> imageView.setImageResource(R.mipmap.garage)
            GARDEN -> imageView.setImageResource(R.mipmap.garden)
            NOROOM -> imageView.setImageResource(R.mipmap.noroom)
            ROOMS -> imageView.setImageResource(R.mipmap.rooms)
            SWIMMING -> imageView.setImageResource(R.mipmap.swimming)
            LAND -> imageView.setImageResource(R.mipmap.land)
            else -> imageView.setImageResource(R.mipmap.ic_launcher_round)
        }
    }
}