package com.sam.faciltyselection.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sam.faciltyselection.viewmodel.FacilitySelectionViewModel
import com.sam.listscreenapplication.viewmodel.CustomViewModelFactory
import com.sam.listscreenapplication.di.ViewModelKey


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FacilitySelectionViewModel::class)
    abstract fun bindFacilitySelectionViewModel(facilitySelectionViewModel: FacilitySelectionViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory
}
