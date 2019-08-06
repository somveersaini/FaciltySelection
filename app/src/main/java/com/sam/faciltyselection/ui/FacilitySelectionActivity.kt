package com.sam.faciltyselection.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sam.faciltyselection.R


class FacilitySelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.facility_selection_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FacilitySelectionFragment.newInstance())
                .commitNow()
        }
    }
}
