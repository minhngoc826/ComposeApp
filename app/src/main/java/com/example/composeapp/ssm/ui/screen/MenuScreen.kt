package com.example.composeapp.ssm.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.R

@Composable
fun MenuScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
//        .fillMaxHeight(0.8f)
//        .background(color = androidx.compose.ui.graphics.Color.Cyan)
        .padding(
            top = 30.dp,
            bottom = 20.dp,
            end = 5.dp
        ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {
        Column(modifier = Modifier
            .fillMaxHeight(0.8f)
            .background(color = androidx.compose.ui.graphics.Color.Cyan)
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Item 1")
            Text(text = "Item 2")
            Text(text = "Item 3333333333")
            Text(text = "Item 4")
            Text(text = "Item 5")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MenuScreen()
}