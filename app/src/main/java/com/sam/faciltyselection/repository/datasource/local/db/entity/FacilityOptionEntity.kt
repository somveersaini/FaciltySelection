package com.sam.faciltyselection.repository.datasource.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facility_options", primaryKeys = arrayOf("facility_id", "option_id"))
data class FacilityOptionEntity (
    @ColumnInfo(name = "facility_id")
    var facility_id: Int,

    @ColumnInfo(name = "facility_name")
    var facility_name: String,

    @ColumnInfo(name = "option_icon")
    var option_icon: String,

    @ColumnInfo(name = "option_name")
    var option_name: String,

    @ColumnInfo(name = "option_id")
    var option_id: Int
)