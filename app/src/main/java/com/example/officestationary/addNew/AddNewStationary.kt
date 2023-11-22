package com.example.officestationary.addNew

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.officestationary.ListActivity
import com.example.officestationary.R
import com.example.officestationary.data.StationaryRepository
import com.example.officestationary.data.dataClasses.Stationary


@Composable
fun AddNewStationary(
    viewModel: AddNewStationaryViewModel = AddNewStationaryViewModel(StationaryRepository())
) {
    val context = LocalContext.current

    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val price = remember { mutableStateOf(" ") }

    val response by viewModel.insertResponseLiveData.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            NameInput(name = name.value, onNameChange = { name.value = it })
            Spacer(Modifier.height(16.dp))
            DescriptionInput(
                description = description.value,
                onDescriptionChange = { description.value = it })
            Spacer(Modifier.height(16.dp))

            // New input field for price
            PriceInput(price = price.value, onPriceChange = { price.value = it })
            Spacer(Modifier.height(16.dp))

            AddNewButton {
                viewModel.saveNewStationary(
                    Stationary(
                        name.value,
                        description.value,
                        price.value.toDoubleOrNull()
                    )
                )
            }
        }

        if (response != null) {
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.Center),
                fontSize = 19.sp,
                text = if (response!!.status == "OK") stringResource(id = R.string.saved_success_msg)
                else stringResource(id = R.string.saved_fail_msg)
            )

            if (response!!.status == "OK") {
                context.startActivity(Intent(context, ListActivity::class.java))
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NameInput(name: String, onNameChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = colorResource(id = R.color.white)
        ),
        value = name,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onNameChange(it) },
        label = {
            Text(stringResource(id = R.string.stationary_name_input_hint))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DescriptionInput(description: String, onDescriptionChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = colorResource(id = R.color.white)
        ),
        value = description,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onDescriptionChange(it) },
        label = {
            Text(stringResource(id = R.string.stationary_desc_input_hint))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PriceInput(price: String, onPriceChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = colorResource(id = R.color.white)
        ),
        value = price,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { onPriceChange(it) },
        label = {
            Text(stringResource(id = R.string.stationary_price_input_hint))
        }
    )
}



@Composable
private fun AddNewButton(onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.black),
            contentColor = Color.White
        )

    ) {
        Text(
            fontSize = 16.sp,
            text = stringResource(id = R.string.save_btn_text)
        )
    }
}