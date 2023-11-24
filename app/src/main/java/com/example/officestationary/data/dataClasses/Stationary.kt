package com.example.officestationary.data.dataClasses

import android.accounts.AuthenticatorDescription

data class Stationary(
    val id: String = "",
    val name: String,
    val description: String?,
    val price: Double?,
    val color: String?,
    val longDescription: String?,
    val url: String?
)