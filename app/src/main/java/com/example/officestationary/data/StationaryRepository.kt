package com.example.officestationary.data

import android.util.Log
import com.example.officestationary.data.network.MyListResponse
import com.example.officestationary.data.network.MyResponse
import com.example.officestationary.data.network.RetrofitInstance
import com.example.officestationary.data.network.stationary.StationaryRequest
import com.example.officestationary.data.network.stationary.StationaryResponse
import com.example.officestationary.data.dataClasses.Stationary
import com.example.officestationary.data.network.MyItemResponse

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
                                id = stationaryFromResponse.id.toString(),
                                name = stationaryFromResponse.name,
                                description = stationaryFromResponse.description,
                                price = stationaryFromResponse.price,
                                color = stationaryFromResponse.color,
                                longDesc = stationaryFromResponse.longDesc,
                                url = stationaryFromResponse.url
                            )
                        )
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return stationaries
    }

    suspend fun insertNewStationary(stationary: Stationary): MyResponse? {
        var response: MyResponse

        try {

            val stationaryRequest =
                StationaryRequest(stationary.name, stationary.description, stationary.price, stationary.color, stationary.longDesc, stationary.url)

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

    suspend fun getStationaryById(stationaryId: String): Stationary? {
        try {
            val response: MyItemResponse<StationaryResponse> =
                RetrofitInstance.stationaryService.getOneStationaryById(stationaryId, "00014241")
            val stationaryFromResponse = response.data

            if (stationaryFromResponse != null) {
                if (stationaryFromResponse.description != null) {
                    return Stationary(
                        id = stationaryId,
                        name = stationaryFromResponse.name,
                        description = stationaryFromResponse.description,
                        price = stationaryFromResponse.price,
                        color = stationaryFromResponse.color,
                        longDesc = stationaryFromResponse.longDesc,
                        url = stationaryFromResponse.url
                    )
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }

    suspend fun getStationaryDetails(stationaryId: String): Stationary? {
        try {
            val response: MyItemResponse<StationaryResponse> =
                RetrofitInstance.stationaryService.getStationaryDetails(
                    stationaryId, "00014241",
                )
            val stationaryFromResponse = response.data

            if (stationaryFromResponse != null) {
                return Stationary(
                    id = stationaryFromResponse.id.toString(),
                    name = stationaryFromResponse.name,
                    description = stationaryFromResponse.description ?: "",
                    price = stationaryFromResponse.price ?: 0.0,
                    color = stationaryFromResponse.color ?: "",
                    longDesc = stationaryFromResponse.longDesc ?: "",
                    url = stationaryFromResponse.url ?: ""
                )
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null;
        }

        return null;
    }

    suspend fun updateStationary(stationary: Stationary): MyItemResponse<StationaryResponse>? {
        val response: MyItemResponse<StationaryResponse>

        try {
            val stationaryRequest =
                StationaryRequest(
                    name = stationary.name,
                    description = stationary.description,
                    price = stationary.price,
                    color = stationary.color,
                    longDesc = stationary.longDesc,
                    url = stationary.url
                )

            response = RetrofitInstance.stationaryService.putStationary(
                stationary.id,
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

