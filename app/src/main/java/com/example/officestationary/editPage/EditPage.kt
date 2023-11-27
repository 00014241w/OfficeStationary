package com.example.officestationary.editPage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.example.officestationary.data.StationaryRepository

@Composable
fun EditPage(
    stationaryId: String,
    viewModel: EditPageViewModel = EditPageViewModel(stationaryId, StationaryRepository())
){
    val localContext = LocalContext.current
    val stationary by viewModel.stationaryLiveData.observeAsState()

    if (stationary !== null){

    }
}