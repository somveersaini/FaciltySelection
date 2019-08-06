package com.sam.faciltyselection.di.module

import android.content.Context
import com.sam.faciltyselection.repository.datasource.DataSource
import com.sam.faciltyselection.repository.datasource.local.LocalDataSource
import com.sam.faciltyselection.repository.datasource.local.db.FacilitySelectionDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    @Singleton
    fun providerFacilitySelectionDatabase(context: Context): FacilitySelectionDatabase {
        return FacilitySelectionDatabase(context)
    }

    @Provides
    fun provideLocalDataSource(localDataSource: LocalDataSource): DataSource = localDataSource
}