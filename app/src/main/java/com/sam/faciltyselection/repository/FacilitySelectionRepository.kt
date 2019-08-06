package com.sam.faciltyselection.repository

import com.sam.faciltyselection.repository.model.FacilitySelectionModel
import io.reactivex.Single

interface FacilitySelectionRepository {
    fun getLocalfacilitySelectionData() : Single<FacilitySelectionModel>

    fun getRemotefacilitySelectionData() : Single<FacilitySelectionModel>

    fun updateDb(facilitySelectionData: FacilitySelectionModel)

}