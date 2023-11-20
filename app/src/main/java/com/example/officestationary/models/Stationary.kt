package com.example.officestationary.models

data class Stationary(
    val name: String,
    val description: String?,
    val price: Double? = null
)