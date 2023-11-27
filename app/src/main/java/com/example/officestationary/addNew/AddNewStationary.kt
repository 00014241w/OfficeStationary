package com.example.officestationary.addNew

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
    val colorOptions = listOf("white", "black", "blue", "green", "orange", "yellow", "pink", "purple", "gray", "multicolor")
    val colorExpanded = remember { mutableStateOf(false) }
    val selectedColor = remember { mutableStateOf(colorOptions[0]) }
    val url = remember { mutableStateOf("") }
    val longDesc = remember { mutableStateOf("")}

    val response by viewModel.insertResponseLiveData.observeAsState()

    Column(
        modifier = Modifier
    ) {
        Text(
            stringResource(id = R.string.create_your_ad),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 20.dp, 0.dp, 0.dp),
            textAlign = TextAlign.Left
        )


        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFE0E0E0))
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                UrlInput(url = url.value, onNameChange = {url.value = it})
                Spacer(Modifier.height(16.dp))
                NameInput(name = name.value, onNameChange = { name.value = it })
                Spacer(Modifier.height(16.dp))
                DescriptionInput(
                    description = description.value,
                    onDescriptionChange = { description.value = it })
                Spacer(Modifier.height(16.dp))
                // New input field for price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    PriceInput(
                        price = price.value,
                        onPriceChange = { price.value = it },
                        modifier = Modifier
                           // .weight(0.8f)
                            .padding(6.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.dollar_sign),
                        modifier = Modifier
                            //.weight(0.2f)
                            .padding(6.dp),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(16.dp))

                Color(
                    isExpanded = colorExpanded.value,
                    onExpandedChanged = { colorExpanded.value = !colorExpanded.value },
                    selectedOptionText = selectedColor.value,
                    onSelectedOptionChanged = { selectedColor.value = it },
                    options = colorOptions
                )

                Spacer(Modifier.height(16.dp))

                LongDescInput(
                    longDesc = longDesc.value,
                    onLongDescChange = { longDesc.value = it })

                AddNewButton {
                    viewModel.saveNewStationary(
                        Stationary(
                            name = name.value,
                            description = description.value,
                            price = price.value.toDoubleOrNull(),
                            color = selectedColor.value,
                            longDesc = longDesc.value,
                            url = url.value
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
            .height(75.dp),
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
private fun UrlInput(url: String, onNameChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = colorResource(id = R.color.bleak_yellow)
        ),
        value = url,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onNameChange(it) },
        label = {
            Text(
                stringResource(id = R.string.stationary_image_input_hint),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PriceInput(price: String, onPriceChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        modifier = Modifier
            .width(300.dp),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Color(
    isExpanded: Boolean,
    onExpandedChanged: (Boolean) -> Unit,
    selectedOptionText: String,
    onSelectedOptionChanged: (String) -> Unit,
    options: List<String>
) {
    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {
        onExpandedChanged(it)
    }) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text(stringResource(id = R.string.stationary_color_menu_hint)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = colorResource(id = R.color.white)
            )
        )

        ExposedDropdownMenu(modifier = Modifier.fillMaxWidth(),
            expanded = isExpanded,
            onDismissRequest = {
                onExpandedChanged(false)
            }) {
            options.forEach { selectionOption ->
                DropdownMenuItem(onClick = {
                    onSelectedOptionChanged(selectionOption)
                    onExpandedChanged(false)
                }, text = { Text(text = selectionOption) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LongDescInput(longDesc: String, onLongDescChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = colorResource(id = R.color.white)
        ),
        value = longDesc,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onLongDescChange(it) },
        label = {
            Text(stringResource(id = R.string.stationary_long_desc_input_hint))
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
            text = stringResource(id = R.string.publish_btn_text)
        )
    }
}