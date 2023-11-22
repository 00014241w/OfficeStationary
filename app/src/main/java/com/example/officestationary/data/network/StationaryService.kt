package com.example.officestationary.data.network

import com.example.officestationary.data.network.stationary.StationaryRequest
import com.example.officestationary.data.network.stationary.StationaryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StationaryService {
    @GET("records/all")
    suspend fun getAllStationaries(
        @Query("student_id") student_id: String
    ): MyListResponse<StationaryResponse>

    @POST("records")
    suspend fun insertNewStationary(
        @Query("student_id") student_id: String,
        @Body stationaryRequest: StationaryRequest
    ): MyResponse
}