package com.sam.faciltyselection.repository.datasource.remote.mapper

import com.sam.faciltyselection.repository.model.*
import com.sam.faciltyselection.repository.datasource.remote.model.RemoteResponse
import javax.inject.Inject

class RemoteResponseToFacilityDataMapper @Inject constructor(){
    fun responseToModel(remoteResponse: RemoteResponse): FacilitySelectionModel{
        val facilityOptionList = mutableListOf<FacilitySelectionListItem>()
        val exclutionList = mutableListOf<Exclusion>()
        remoteResponse.facilities.forEach { (facility_id, facility_name, options) ->
            facilityOptionList.add(FacilitySection(facility_name))
            options.forEach { (option_icon, option_name, option_id) ->
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
        }
        remoteResponse.exclusions.forEach { exclusions ->
            if (exclusions.size == 2){
               exclutionList.add(
                    Exclusion(
                        exclusions[0].facility_id,
                        exclusions[0].options_id,
                        exclusions[1].facility_id,
                        exclusions[1].options_id
                    )
                )
            }
        }
        return FacilitySelectionModel(facilityOptionList, exclutionList)
    }
}