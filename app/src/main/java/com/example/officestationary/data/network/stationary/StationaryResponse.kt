package com.example.officestationary.data.network.stationary

import android.accounts.AuthenticatorDescription
import com.google.gson.annotations.SerializedName

data class StationaryResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("price")
    val price: Double,
    @SerializedName("color")
    val color: String,
    @SerializedName("type")
    val longDescription: String,
    @SerializedName("url")
    val url: String?
)
