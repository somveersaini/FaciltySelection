package com.sam.faciltyselection.repository.datasource.local.db.dao

import androidx.room.*
import com.sam.faciltyselection.repository.datasource.local.db.entity.ExclusionEntity
import com.sam.faciltyselection.repository.model.Exclusion
import io.reactivex.Single

@Dao
interface ExclusionDao {
    @Query("SELECT * FROM exclusions")
    fun getAll(): Single<List<ExclusionEntity>>

    @Query("DELETE FROM exclusions")
    fun deleteAll()

    @Insert
    fun insertAll(exclusionEntityList: List<ExclusionEntity>)

    @Delete
    fun delete(exclusionEntity: ExclusionEntity)

    @Update
    fun updateTodo(vararg exclusionEntity: ExclusionEntity)
}