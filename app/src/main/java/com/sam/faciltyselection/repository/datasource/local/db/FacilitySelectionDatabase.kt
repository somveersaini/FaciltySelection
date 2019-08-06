package com.sam.faciltyselection.repository.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sam.faciltyselection.repository.datasource.local.db.dao.ExclusionDao
import com.sam.faciltyselection.repository.datasource.local.db.dao.FacilityOptionDao
import com.sam.faciltyselection.repository.datasource.local.db.entity.ExclusionEntity
import com.sam.faciltyselection.repository.datasource.local.db.entity.FacilityOptionEntity

@Database(
    entities = [FacilityOptionEntity::class, ExclusionEntity::class],
    version = 1
)
abstract class FacilitySelectionDatabase : RoomDatabase(){
    abstract fun FacilityOptionDao(): FacilityOptionDao
    abstract fun ExclusionDao(): ExclusionDao

    companion object {
        @Volatile private var instance: FacilitySelectionDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            FacilitySelectionDatabase::class.java, "facility_selection.db")
            .build()
    }
}