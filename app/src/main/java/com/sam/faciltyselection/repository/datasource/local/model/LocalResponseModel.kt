package com.sam.faciltyselection.repository.datasource.local.model

import com.sam.faciltyselection.repository.datasource.local.db.entity.ExclusionEntity
import com.sam.faciltyselection.repository.datasource.local.db.entity.FacilityOptionEntity

data class LocalResponseModel(
    val facilityOptionEntityList: List<FacilityOptionEntity>,
    val exclusionEntityList: List<ExclusionEntity>
)