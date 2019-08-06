package com.sam.faciltyselection.repository.model

data class FacilitySelectionModel(
    val facilityOption: List<FacilitySelectionListItem>,
    val exclusion: List<Exclusion>
)

data class FacilityOption(
    val facility_id: Int,
    val facility_name: String,
    val option_icon: String,
    val option_name: String,
    val option_id: Int,
    var selected: Boolean = false
) : FacilitySelectionListItem


data class Exclusion(
    val first_facility_id: Int,
    val first_option_id: Int,
    val second_facility_id: Int,
    val second_option_id: Int
)

data class FacilitySection(
    val name: String
): FacilitySelectionListItem

interface FacilitySelectionListItem