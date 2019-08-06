package com.sam.faciltyselection.di.module

import com.sam.faciltyselection.repository.FacilitySelectionRepository
import com.sam.faciltyselection.repository.FacilitySelectionRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindDataRepository(facilitySelectionRepository: FacilitySelectionRepositoryImpl): FacilitySelectionRepository
}