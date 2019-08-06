package com.sam.faciltyselection.repository.datasource

import com.sam.faciltyselection.repository.model.FacilitySelectionModel
import io.reactivex.Single

interface DataSource {
    fun getFacilitySelectionData(): Single<FacilitySelectionModel>
}