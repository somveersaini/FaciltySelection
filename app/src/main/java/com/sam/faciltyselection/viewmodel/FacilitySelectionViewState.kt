package com.sam.faciltyselection.viewmodel

import com.sam.faciltyselection.repository.model.FacilitySelectionModel

sealed class FacilitySelectionViewState

data class SuccessState(val facilitySelectionModel: FacilitySelectionModel) : FacilitySelectionViewState()

object LoadingState : FacilitySelectionViewState()

data class ErrorState(val errorMessage: String) : FacilitySelectionViewState()