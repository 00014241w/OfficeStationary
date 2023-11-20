package com.example.officestationary.data

import com.example.officestationary.data.network.MyListResponse
import com.example.officestationary.data.network.RetrofitInstance
import com.example.officestationary.data.network.stationary.StationaryResponse
import com.example.officestationary.models.Stationary

class StationaryRepository {
    suspend fun getStationaryList(): List<Stationary> {
        val stationaries = mutableListOf<Stationary>()
        try {
            val response: MyListResponse<StationaryResponse> =
                RetrofitInstance.stationaryService.getAllStationaries("00014241")
            val stationariesFromResponse = response.data


            if (stationariesFromResponse != null) {
                for (stationaryFromResponse in stationariesFromResponse) {
                    if (stationaryFromResponse.description != null) {
                        stationaries.add(
                            Stationary(
                                stationaryFromResponse.name.uppercase(),
                                stationaryFromResponse.description
                            )
                        )
                    }
                }
            }
        } catch (ex: Exception){
            ex.printStackTrace()
        }

        return stationaries
    }
}