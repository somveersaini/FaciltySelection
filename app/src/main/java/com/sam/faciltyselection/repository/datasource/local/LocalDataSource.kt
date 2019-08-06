package com.sam.faciltyselection.repository.datasource.local

import com.sam.faciltyselection.repository.datasource.DataSource
import com.sam.faciltyselection.repository.datasource.local.db.FacilitySelectionDatabase
import com.sam.faciltyselection.repository.datasource.local.mapper.LocalResponseToFacilityDataMapper
import com.sam.faciltyselection.repository.datasource.local.model.LocalResponseModel
import com.sam.faciltyselection.repository.model.FacilitySelectionModel
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    val facilitySelectionDatabase: FacilitySelectionDatabase,
    val localResponseToFacilityDataMapper: LocalResponseToFacilityDataMapper
) : DataSource {

    override fun getFacilitySelectionData(): Single<FacilitySelectionModel> {
        val facilityOptionListSingle = facilitySelectionDatabase.FacilityOptionDao().getAll()
        val exclusionListSingle = facilitySelectionDatabase.ExclusionDao().getAll()
        return facilityOptionListSingle.zipWith(
            exclusionListSingle,
            BiFunction {
                facilityOptionList,
                exclusionList
                 -> localResponseToFacilityDataMapper.responseToModel(LocalResponseModel(facilityOptionList, exclusionList))
            }
        )
    }

    fun insertFacilitySelectionData(facilitySelectionData: FacilitySelectionModel){
        facilitySelectionDatabase.FacilityOptionDao().deleteAll()
        facilitySelectionDatabase.ExclusionDao().deleteAll()
        val localResponseModel = localResponseToFacilityDataMapper.modelToResponse(facilitySelectionData)
        facilitySelectionDatabase.FacilityOptionDao().insertAll(localResponseModel.facilityOptionEntityList)
        facilitySelectionDatabase.ExclusionDao().insertAll(localResponseModel.exclusionEntityList)
    }
}