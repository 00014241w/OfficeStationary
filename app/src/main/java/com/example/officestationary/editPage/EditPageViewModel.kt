package com.example.officestationary.editPage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officestationary.data.StationaryRepository
import com.example.officestationary.data.dataClasses.Stationary
import com.example.officestationary.data.network.MyResponse
import kotlinx.coroutines.launch

class EditPageViewModel(
    stationaryId: String,
    private val stationaryRepository: StationaryRepository
) : ViewModel() {
    val stationaryLiveData: MutableLiveData<Stationary> by lazy {
        MutableLiveData<Stationary>()
    }
    val stationaryUpdateResLiveData: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }

    init {
        getStationary(stationaryId)
    }

    private fun getStationary(stationaryId: String) {
        viewModelScope.launch {
            val stationary = stationaryRepository.getStationaryDetails(stationaryId)
            stationaryLiveData.value = stationary
        }
    }
    private fun changeStationary(stationary: Stationary){
        viewModelScope.launch {
            try{
                val response = stationaryRepository.updateStationary(stationary)
                stationaryUpdateResLiveData.value = response

                Log.d("Update_response", response.toString())
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}