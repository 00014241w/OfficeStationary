package com.example.officestationary.data.network

import com.google.gson.annotations.SerializedName

data class MyItemResponse<T>(
    @SerializedName("data")
    val data: T?
) : MyResponse()
