package com.example.officestationary.addNew

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officestationary.data.StationaryRepository
import com.example.officestationary.data.network.MyResponse
import com.example.officestationary.data.dataClasses.Stationary
import kotlinx.coroutines.launch

class AddNewStationaryViewModel(private val stationaryRepository: StationaryRepository) : ViewModel()
{    val insertResponseLiveData: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }

    fun saveNewStationary(stationary: Stationary) {
        viewModelScope.launch {
            try {

                val response = stationaryRepository.insertNewStationary(stationary)
                insertResponseLiveData.value = response

                Log.d("Update_response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}