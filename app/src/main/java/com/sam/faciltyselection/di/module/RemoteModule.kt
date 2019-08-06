package com.sam.faciltyselection.di.module

import com.sam.faciltyselection.config.Config
import com.sam.faciltyselection.repository.datasource.DataSource
import com.sam.faciltyselection.repository.datasource.remote.RemoteApiService
import com.sam.faciltyselection.repository.datasource.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun providerRemoteApiService(): RemoteApiService {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return Retrofit
            .Builder()
            .client(httpClient.build())
            .baseUrl(Config.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteApiService::class.java)
    }

    @Provides
    fun provideRemoteDataSource(remoteDataSource: RemoteDataSource): DataSource = remoteDataSource
}