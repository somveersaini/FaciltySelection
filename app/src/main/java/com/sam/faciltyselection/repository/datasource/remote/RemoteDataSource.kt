package com.sam.faciltyselection.repository.datasource.remote

import com.sam.faciltyselection.repository.datasource.DataSource
import com.sam.faciltyselection.repository.model.FacilitySelectionModel
import com.sam.faciltyselection.repository.datasource.remote.mapper.RemoteResponseToFacilityDataMapper
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    val listApiService: RemoteApiService,
    val mapper: RemoteResponseToFacilityDataMapper
) : DataSource {

    override fun getFacilitySelectionData(): Single<FacilitySelectionModel> {
        return listApiService.getListData()
            .map { mapper.responseToModel(it) }
    }
}