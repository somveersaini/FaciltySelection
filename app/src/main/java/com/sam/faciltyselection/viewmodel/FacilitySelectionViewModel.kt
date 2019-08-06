package com.sam.faciltyselection.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sam.faciltyselection.repository.FacilitySelectionRepository
import com.sam.faciltyselection.repository.model.Exclusion
import com.sam.faciltyselection.repository.model.FacilityOption
import com.sam.faciltyselection.repository.model.FacilitySelectionModel
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FacilitySelectionViewModel @Inject constructor() : ViewModel() {

    private val viewStateLiveData = MutableLiveData<FacilitySelectionViewState>()

    @Inject
    lateinit var repository: FacilitySelectionRepository

    fun getFacilitySelectionViewState() = viewStateLiveData


    fun fetchFacilitySelectionData() {
        if (viewStateLiveData.value == null) {
            viewStateLiveData.postValue(LoadingState)
            val localFacilitySelectionDataSingle = repository.getLocalfacilitySelectionData()
            localFacilitySelectionDataSingle.subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<FacilitySelectionModel>() {
                    override fun onSuccess(facilitySelectionData: FacilitySelectionModel) {
                        if (facilitySelectionData.facilityOption.isEmpty()){
                            Log.d("VM", "No data in DB")
                            fetchRemoteFacilitySelectionData()
                        }
                        else {
                            viewStateLiveData.postValue(
                                SuccessState(facilitySelectionData)
                            )
                        }
                    }

                    override fun onError(error: Throwable) {
                        //viewStateLiveData.postValue(ErrorState(error.message ?: " "))
                        fetchRemoteFacilitySelectionData()
                    }
                })
        }
    }

    private fun fetchRemoteFacilitySelectionData() {
        val remoteFacilitySelectionDataSingle = repository.getRemotefacilitySelectionData()
        remoteFacilitySelectionDataSingle.subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<FacilitySelectionModel>() {
                override fun onSuccess(facilitySelectionData: FacilitySelectionModel) {
                    viewStateLiveData.postValue(
                        SuccessState(facilitySelectionData)

                    )
                    repository.updateDb(facilitySelectionData)
                }

                override fun onError(error: Throwable) {
                    viewStateLiveData.postValue(ErrorState(error.message ?: " "))
                }
            })
    }

    fun select(position: Int): List<Int>{
        val listChange = mutableListOf<Int>()
        if (viewStateLiveData.value != null) {
            val viewState = viewStateLiveData.value
            if (viewState is SuccessState){
                val facilityList = viewState.facilitySelectionModel.facilityOption
                val exclusionList = viewState.facilitySelectionModel.exclusion
                val current = facilityList.get(position)
                if (current is FacilityOption) {
                    val selectedList = mutableListOf<Pair<Int, Int>>()
                    for(facility in facilityList){
                        if (facility is FacilityOption){
                            if (facility.selected) {
                                selectedList.add(Pair(facility.facility_id, facility.option_id))
                            }
                        }
                    }
                    if (current.selected){
                        current.selected = false
                        listChange.add(position)
                        viewStateLiveData.postValue(viewState)
                        return listChange
                    }
                    selectedList.forEach {
                        if (exclusionList.contains(Exclusion(current.facility_id, current.option_id, it.first, it.second))) return listChange
                        if (exclusionList.contains(Exclusion(it.first, it.second, current.facility_id, current.option_id))) return listChange
                    }

                    var i = 0
                    for(facility in facilityList){
                        if (facility is FacilityOption){
                            if (facility.facility_id == current.facility_id){
                                if (facility.selected) {
                                    facility.selected = false
                                    listChange.add(i)
                                }
                            }
                        }
                        i++
                    }
                    current.selected = true
                    listChange.add(position)
                    viewStateLiveData.postValue(viewState)
                    return listChange
                }

            }
        }
        return listChange
    }

}
