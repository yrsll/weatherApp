package com.example.weatherappwithneco.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherappwithneco.data.WeatherModel
import com.example.weatherappwithneco.ui.theme.BlueLight

@Composable
fun MainList(list:List<WeatherModel>, currentDay: MutableState<WeatherModel>){
    LazyColumn(modifier = Modifier.fillMaxSize()){
        itemsIndexed(list
        ){
          _,item ->
            ListItem(item, currentDay)
        }
    }
}
@Composable

fun ListItem(item: WeatherModel, currentDay:MutableState<WeatherModel>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp)
            .clickable {
                if (item.hours.isEmpty()) return@clickable
                currentDay.value = item
            },
        backgroundColor = BlueLight,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(modifier = Modifier.padding(start = 8.dp, top = 5.dp, bottom = 5.dp)) {
                Text(text = item.time)
                Text(text = item.condition, color = Color.White)

            }
            Text(text = item.currentTemp.ifEmpty { "${item.maxTemp}/${item.minTemp}" }, color = Color.White,
                style = TextStyle(fontSize = 25.sp))
            AsyncImage(
                model = "https:${item.icon}",
                contentDescription = "im5",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(35.dp)
            )
        }
    }
}
@Composable
fun dialogSearch(dialogState:MutableState<Boolean>, onSubmit:(String)->Unit){
    val dialogText = remember{ mutableStateOf("") }
    AlertDialog(onDismissRequest = {
                                   dialogState.value=false
    },
    confirmButton = {
        TextButton(onClick = {  onSubmit(dialogText.value)
            dialogState.value=false}
       ) {
            Text(text = "OK")

        }
    },
    dismissButton = {
        TextButton(onClick = { dialogState.value=false}) {
            Text(text = "Cancel")

        }
    },
    title = {
        Column(modifier = Modifier.fillMaxWidth()) {


        Text(text = "Edit city name:")
            TextField(value = dialogText.value, onValueChange = {
                dialogText.value=it
            })
    }})


}