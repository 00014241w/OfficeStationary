package com.example.officestationary.data.network.stationary

import com.google.gson.annotations.SerializedName

data class StationaryRequest (
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("price")
    val price: Double?
)