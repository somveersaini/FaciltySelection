package com.sam.faciltyselection.di

import android.app.Application
import com.sam.faciltyselection.FacilitySelectionApplication
import com.sam.faciltyselection.di.module.*
import com.sam.faciltyselection.ui.FacilitySelectionActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        RemoteModule::class,
        LocalModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        UiModule::class
    )
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: FacilitySelectionApplication)
}