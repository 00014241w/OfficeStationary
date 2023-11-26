package com.example.officestationary.data.dataClasses

data class Stationary(
    val id: String = "",
    val name: String,
    val description: String?,
    val price: Double?,
    val color: String?,
    val longDesc: String?,
    val url: String?
)