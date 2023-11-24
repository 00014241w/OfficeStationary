package com.example.officestationary.list

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.officestationary.R
import com.example.officestationary.data.StationaryRepository
import com.example.officestationary.data.dataClasses.Stationary


@Composable
fun StationaryList(
    viewModel: StationaryListViewModel = StationaryListViewModel(StationaryRepository()),
    onAddNewStationaryClick: () -> Unit,
    onPlayerBtnClick: () -> Unit,
    onStationaryClick: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Text(
                stringResource(id = R.string.recommended_for_you),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 20.dp, 0.dp, 10.dp),
                textAlign = TextAlign.Left
            )

            val stationaries by viewModel.stationariesLiveData.observeAsState()

            if (!stationaries.isNullOrEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(0.dp, 0.dp, 0.dp, 90.dp)
                ) {
                    items(items = stationaries!!.toList(), itemContent = { item ->
                        StationaryItem(stationary = item, onStationaryClick)
                    })
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            FloatingActionButton(
                modifier = Modifier,
                containerColor = colorResource(id = R.color.white),
                onClick = onAddNewStationaryClick
            ) {
                Text(
                    stringResource(id = R.string.btn_add_new_movie),
                    modifier = Modifier.padding(15.dp, 5.dp),
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp
                )
            }

            FloatingActionButton(
                modifier = Modifier,
                containerColor = colorResource(id = R.color.white),
                onClick = onPlayerBtnClick
            ) {
                Text(
                    stringResource(id = R.string.btn_go_player),
                    modifier = Modifier.padding(15.dp, 5.dp),
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp
                )
            }
        }
    }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun StationaryItem(stationary: Stationary, onStationaryClick: (String) -> Unit){
    ElevatedCard(
        modifier = Modifier
            .padding(12.dp, 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white), //Card background color
            contentColor = Color.DarkGray  //Card content color,e.g.text
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RectangleShape // Remove border radius
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onStationaryClick(stationary.id)
                }
        ) {
            // Image on the left taking 30% of the card width
            StationaryItemImg(url = stationary.url, modifier = Modifier
                .width(0.dp)
                .weight(0.3f)
                .padding(6.dp)
                .fillMaxHeight()
            )

            // Content in a column taking 70% of the card width
            Column(
                modifier = Modifier
                    .width(0.dp)
                    .weight(0.7f)
                    .fillMaxHeight()
                    .padding(12.dp)
            ) {
                StationaryItemName(name = stationary.name)
                if (!stationary.description.isNullOrEmpty())
                    StationaryItemDesc(desc = stationary.description)
                StationaryItemPrice(price = stationary.price)
            }
        }

    }
}
@Composable
private fun StationaryItemImg(url: String?, modifier: Modifier = Modifier) {
    val imageUrl = url ?: ""
    Box(
        modifier = modifier
    ) {
        if (imageUrl.isNotEmpty()) {
            // Display image if URL is not empty
            AsyncImage(
                model = imageUrl,
                contentDescription = "Item image",
                placeholder = painterResource(id = R.drawable.sudoimage),
                error = painterResource(id = R.drawable.sudoimage),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        } else {
            // Display placeholder image if URL is empty
            Image(
                painter = painterResource(id = R.drawable.sudoimage),
                contentDescription = null, // Set contentDescription to null for placeholder images
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
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