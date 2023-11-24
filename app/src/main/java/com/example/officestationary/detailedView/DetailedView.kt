package com.example.officestationary.detailedView

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.officestationary.R
import com.example.officestationary.data.StationaryRepository
import com.example.officestationary.data.dataClasses.Stationary

@Composable
fun DetailedView(
    stationaryId: String,
    viewModel: DetailedViewModel = DetailedViewModel(stationaryId, StationaryRepository())
){
    val stationary by viewModel.stationaryLiveData.observeAsState()

    if (stationary != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFE0E0E0))
                .padding(16.dp)
        ) {
            Name(name = stationary!!.name)
            if (stationary!!.description != null) {
                Description(description = stationary!!.description!!)
            }

            if(stationary!!.url != null){
                AsyncImage(
                    model = stationary!!.url,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    placeholder = painterResource(id = R.drawable.sudoimage),
                    error = painterResource(id = R.drawable.sudoimage),
                    contentDescription = "The item image"
                )
            }

            if (stationary!!.price != null) {
                Price(price = stationary!!.price!!)
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}


@Composable
private fun Name(name: String) {
    Text(
        text = name,
        color = Color.Black,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Price(price: Double) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = "$${String.format("%.2f", price)}",
        color = Color.Black,
        fontSize = 22.sp,
    )
}

@Composable
private fun Description(description: String) {
    Text(
        modifier = Modifier.padding(top = 10.dp),
        text = description,
        color = Color.DarkGray,
        fontSize = 22.sp,
    )
}