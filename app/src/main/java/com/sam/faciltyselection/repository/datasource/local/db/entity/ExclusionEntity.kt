package com.sam.faciltyselection.repository.datasource.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exclusions")
data class ExclusionEntity(
    @PrimaryKey(autoGenerate = true)
    var uid: Int,

    @ColumnInfo(name = "first_facility_id")
    var first_facility_id: Int,

    @ColumnInfo(name = "first_option_id")
    var first_option_id: Int,

    @ColumnInfo(name = "second_facility_id")
    var second_facility_id: Int,

    @ColumnInfo(name = "second_option_id")
    var second_option_id: Int
)