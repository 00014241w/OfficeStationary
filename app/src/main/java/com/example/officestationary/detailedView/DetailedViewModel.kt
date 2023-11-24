package com.example.officestationary.detailedView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officestationary.data.StationaryRepository
import com.example.officestationary.data.dataClasses.Stationary
import kotlinx.coroutines.launch

class DetailedViewModel(
    stationaryId: String,
    private val stationaryRepository: StationaryRepository
) : ViewModel() {

    val stationaryLiveData: MutableLiveData<Stationary> by lazy{
        MutableLiveData<Stationary>()
    }
    init {
        getStationaryByIdFromRemoteDb(stationaryId)
    }
    private fun getStationaryByIdFromRemoteDb(stationaryId: String){
        viewModelScope.launch{
            if(!stationaryId.isNullOrEmpty()){
                val stationary = stationaryRepository.getStationaryById(stationaryId)
                stationaryLiveData.value = stationary
            }
        }
    }
}