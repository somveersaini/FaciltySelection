package com.sam.faciltyselection.repository.datasource.local.mapper

import com.sam.faciltyselection.repository.datasource.local.db.entity.ExclusionEntity
import com.sam.faciltyselection.repository.datasource.local.db.entity.FacilityOptionEntity
import com.sam.faciltyselection.repository.datasource.local.model.LocalResponseModel
import com.sam.faciltyselection.repository.model.*
import javax.inject.Inject

class LocalResponseToFacilityDataMapper @Inject constructor(){
    fun responseToModel(localResponseModel: LocalResponseModel): FacilitySelectionModel{
        val facilityOptionList = mutableListOf<FacilitySelectionListItem>()
        val exclusionList = mutableListOf<Exclusion>()
        localResponseModel.facilityOptionEntityList.forEach { (facility_id, facility_name, option_icon, option_name, option_id) ->
            if (!facilityOptionList.contains(FacilitySection(facility_name))) facilityOptionList.add(FacilitySection(facility_name))
            facilityOptionList.add(
                FacilityOption(
                    facility_id,
                    facility_name,
                    option_icon,
                    option_name,
                    option_id
                )
            )
        }
        localResponseModel.exclusionEntityList.forEach { item ->
            exclusionList.add(
                Exclusion(
                    item.first_facility_id,
                    item.first_option_id,
                    item.second_facility_id,
                    item.second_option_id
                )
            )
        }
        return FacilitySelectionModel(facilityOptionList, exclusionList)
    }

    fun modelToResponse(facilitySelectionModel: FacilitySelectionModel): LocalResponseModel{

        val facilityOptionEntityList = mutableListOf<FacilityOptionEntity>()
        val exclusionEntityList = mutableListOf<ExclusionEntity>()
        facilitySelectionModel.facilityOption.forEach { item ->
            if (item is FacilityOption){
                facilityOptionEntityList.add(
                    FacilityOptionEntity(
                        item.facility_id,
                        item.facility_name,
                        item.option_icon,
                        item.option_name,
                        item.option_id
                    )
                )
            }
        }

        facilitySelectionModel.exclusion.forEach { item ->
            exclusionEntityList.add(
                ExclusionEntity(
                    0,
                    item.first_facility_id,
                    item.first_option_id,
                    item.second_facility_id,
                    item.second_option_id
                )
            )
        }

        return LocalResponseModel(facilityOptionEntityList, exclusionEntityList)
    }
}