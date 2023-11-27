package com.example.officestationary.data.network

import com.example.officestationary.data.dataClasses.Stationary
import com.example.officestationary.data.network.stationary.StationaryRequest
import com.example.officestationary.data.network.stationary.StationaryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
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

    @GET("records/{record_id}")
    suspend fun getOneStationaryById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyItemResponse<StationaryResponse>

    @GET("records/{phoneId}")
    suspend fun getStationaryDetails(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String,
    ): MyItemResponse<StationaryResponse>

    @PUT("records/{record_id}")
    suspend fun putStationary(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String,
        @Body stationary: StationaryRequest,
    ): MyItemResponse<StationaryResponse>
}