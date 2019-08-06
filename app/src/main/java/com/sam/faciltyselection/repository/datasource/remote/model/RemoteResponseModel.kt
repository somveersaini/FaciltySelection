package com.sam.faciltyselection.repository.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteResponse(
    @SerializedName("facilities")
    val facilities: List<Facility>,

    @SerializedName("exclusions")
    val exclusions: List<List<Exclusion>>
)

data class Facility(
    @SerializedName("facility_id")
    val facility_id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("options")
    val options: List<Option>
)

data class Option(
    @SerializedName("icon")
    val icon: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    val id: Int
)


data class Exclusion(
    @SerializedName("facility_id")
    val facility_id: Int,

    @SerializedName("options_id")
    val options_id: Int
)

