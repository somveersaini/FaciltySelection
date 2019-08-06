package com.sam.faciltyselection.repository

import com.sam.faciltyselection.repository.datasource.local.LocalDataSource
import com.sam.faciltyselection.repository.model.FacilitySelectionModel
import com.sam.faciltyselection.repository.datasource.remote.RemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class FacilitySelectionRepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource, val localDataSource: LocalDataSource) : FacilitySelectionRepository{

    override fun getRemotefacilitySelectionData() : Single<FacilitySelectionModel> {
        return remoteDataSource.getFacilitySelectionData()
    }

    override fun getLocalfacilitySelectionData() : Single<FacilitySelectionModel> {
        return localDataSource.getFacilitySelectionData()
    }

    override fun updateDb(facilitySelectionData: FacilitySelectionModel) {
        localDataSource.insertFacilitySelectionData(facilitySelectionData)
    }

}
