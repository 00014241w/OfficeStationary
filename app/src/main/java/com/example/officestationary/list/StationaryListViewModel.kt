package com.example.officestationary.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officestationary.data.StationaryRepository
import com.example.officestationary.data.dataClasses.Stationary
import kotlinx.coroutines.launch

class StationaryListViewModel(
    private val stationaryRepository: StationaryRepository
) : ViewModel() {

    val stationariesLiveData: MutableLiveData<List<Stationary>> by lazy {
        MutableLiveData<List<Stationary>>()
    }

    init {
        getAllStationaries()
    }

    fun getAllStationaries() {
        viewModelScope.launch {
            val stationaries = stationaryRepository.getStationaryList()
            stationariesLiveData.value = stationaries
        }
    }
}