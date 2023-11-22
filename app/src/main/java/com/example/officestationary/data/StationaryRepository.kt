package com.example.officestationary.data

import android.util.Log
import com.example.officestationary.data.network.MyListResponse
import com.example.officestationary.data.network.MyResponse
import com.example.officestationary.data.network.RetrofitInstance
import com.example.officestationary.data.network.stationary.StationaryRequest
import com.example.officestationary.data.network.stationary.StationaryResponse
import com.example.officestationary.data.dataClasses.Stationary

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
                                stationaryFromResponse.name,
                                stationaryFromResponse.description,
                                stationaryFromResponse.price
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

    suspend fun insertNewStationary(stationary: Stationary): MyResponse? {
        var response: MyResponse

        try {

            val stationaryRequest =
                StationaryRequest(stationary.name, stationary.description)

            response = RetrofitInstance.stationaryService.insertNewStationary(
                "00014241",
                stationaryRequest
            )

            Log.d("Update_response", response.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return response
    }
}