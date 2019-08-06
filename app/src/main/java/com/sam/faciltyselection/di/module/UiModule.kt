package com.sam.faciltyselection.di.module

import com.sam.faciltyselection.ui.FacilitySelectionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {
    @ContributesAndroidInjector
    abstract fun contributeFacilitySelectionFragment(): FacilitySelectionFragment
}