package com.example.officestationary.navigation

sealed class Screens(val route: String) {
    object StationariesListScreen: Screens("stationaries_list")
    object PlayerPageScreen: Screens("player")
}
