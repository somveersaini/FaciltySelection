package com.sam.faciltyselection.repository.datasource.local.db.dao

import androidx.room.*
import com.sam.faciltyselection.repository.datasource.local.db.entity.FacilityOptionEntity
import com.sam.faciltyselection.repository.model.FacilityOption
import io.reactivex.Single

@Dao
interface FacilityOptionDao {
    @Query("SELECT * FROM facility_options")
    fun getAll(): Single<List<FacilityOptionEntity>>

    @Query("DELETE FROM facility_options")
    fun deleteAll()

    @Insert
    fun insertAll(facilityOptionEntityList: List<FacilityOptionEntity>)

    @Delete
    fun delete(facilityOptionEntity: FacilityOptionEntity)

    @Update
    fun update(vararg facilityOptionEntity: FacilityOptionEntity)
}