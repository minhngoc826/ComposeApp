package com.example.composeapp.ssm.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.R
import com.example.composeapp.common.SubScreen
import com.example.composeapp.common.theme.ComposeAppTheme

@Composable
fun MainScreen(
    onNewActivityClicked: () -> Unit,
    onMenuClicked: () -> Unit,
    onSenderBtnClicked: () -> Unit,
    onReceiverBtnClicked: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(
            top = dimensionResource(id = R.dimen.screen_top_padding),
            bottom = dimensionResource(id = R.dimen.screen_bottom_padding)
        ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //    Menu
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Cloud",
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.menu_icon_padding))
                    .size(dimensionResource(id = R.dimen.menu_icon_size))
                    .clickable { onNewActivityClicked() }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "SdCard",
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.menu_icon_padding))
                    .size(dimensionResource(id = R.dimen.menu_icon_size))
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.menu_icon_padding))
                    .size(dimensionResource(id = R.dimen.menu_icon_size))
                    .clickable(onClick = { onMenuClicked() })
            )
        }
        // Title
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Transfer your data",
                fontSize = 30.sp,
                fontWeight = Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(text = "What should this phone do?")
        }
        //    Images
        Row(
            modifier = Modifier
//            .weight(3F)
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 50.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
//            Spacer(modifier = Modifier.weight(0.5F))
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "One",
                Modifier
                    .weight(1F)
                    .padding(10.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Two",
                modifier = Modifier
                    .weight(1F)
                    .padding(10.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Three",
                Modifier
                    .weight(1F)
                    .padding(10.dp)
            )
//            Spacer(modifier = Modifier.weight(0.5F))
        }
        // Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 30.dp, end = 30.dp)
                .clickable(onClick = { onSenderBtnClicked() })
                .background(Color.LightGray, RoundedCornerShape(20.dp)),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Send",
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 20.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "Send data", fontSize = 18.sp,
                fontWeight = Bold,
                modifier = Modifier.padding(30.dp)
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .clickable { onReceiverBtnClicked() }
            .background(Color.LightGray, RoundedCornerShape(20.dp)),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Receive",
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 20.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "Receive data", fontSize = 18.sp,
                fontWeight = Bold,
                modifier = Modifier.padding(30.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ComposeAppTheme {
        MainScreen( { }, { }, { }, { } )
    }
}