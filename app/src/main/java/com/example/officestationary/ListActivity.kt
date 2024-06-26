package com.example.officestationary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.officestationary.list.StationaryList
import com.example.officestationary.navigation.Navigation
import com.example.officestationary.navigation.Screens
import com.example.officestationary.ui.theme.OfficeStationaryTheme

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OfficeStationaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFE0E0E0)
                ) {
                    val navController = rememberNavController()
                    Navigation(navController = navController, context = this)

                    navController.navigate(Screens.StationariesListScreen.route)
                }
            }
        }
    }
}

