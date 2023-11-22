package com.example.officestationary.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import com.example.officestationary.addNew.AddNewActivity
import com.example.officestationary.list.StationaryList
import com.example.officestationary.profile.PlayerScreen


import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Navigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = Screens.StationariesListScreen.route) {
        composable(Screens.StationariesListScreen.route) {
            StationaryList(onAddNewStationaryClick = {
                context.startActivity(Intent(context, AddNewActivity::class.java))
            }, onPlayerBtnClick = {
                navController.navigate(Screens.PlayerPageScreen.route)
            })
        }

        composable(Screens.PlayerPageScreen.route) {
            PlayerScreen()
        }
    }
}