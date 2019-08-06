package com.sam.faciltyselection.repository.datasource.remote

import com.sam.faciltyselection.config.Config
import com.sam.faciltyselection.repository.datasource.remote.model.RemoteResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteApiService {

    @GET("/${Config.ASSIGNMENT_KEY}")
    fun getListData(): Single<RemoteResponse>
}