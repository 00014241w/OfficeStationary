package com.example.officestationary.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.officestationary.R
import com.example.officestationary.data.StationaryRepository
import com.example.officestationary.models.Stationary

@Composable
fun StationaryList(
    viewModel: StationaryListViewModel = StationaryListViewModel(StationaryRepository())
) {
    //val stationaries = StationaryRepository().getStationaryList()
    val stationaries by viewModel.stationariesLiveData.observeAsState()


    if (!stationaries.isNullOrEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items = stationaries!!, itemContent = { item ->
                StationaryItem(stationary = item)
            })
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun StationaryItem(stationary: Stationary){
    ElevatedCard(
        modifier = Modifier
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.bleak_yellow), //Card background color
            contentColor = Color.DarkGray  //Card content color,e.g.text
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    )
    {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            StationaryItemName(name = stationary.name)
            if (!stationary.description.isNullOrEmpty())
                StationaryItemDesc(desc = stationary.description)
                StationaryItemPrice(price = stationary.price)
        }
    }
}

@Composable
private fun StationaryItemName(name: String) {
    Text(
        text = name,
        fontSize = 21.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}

@Composable
private fun StationaryItemDesc(desc: String) {
    Text(
        text = desc,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = Color.Gray,
        fontSize = 18.sp,
        textAlign = TextAlign.Left
    )
}

@Composable
private fun StationaryItemPrice(price: Double?) {
    price?.let {
        Text(
            text = "$${String.format("%.2f", price)}", // Format the price with two decimal places
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp)
        )
    }
}