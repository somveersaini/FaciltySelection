package com.sam.faciltyselection

import android.app.Application
import androidx.fragment.app.Fragment
import com.sam.faciltyselection.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class FacilitySelectionApplication : Application(), HasSupportFragmentInjector {


    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        initInjector()
    }

    private fun initInjector() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector


}