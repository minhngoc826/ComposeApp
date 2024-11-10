package com.example.composeapp.navigation.profile.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeapp.common.Logger
import com.example.composeapp.navigation.profile.viewmodel.UserViewModel

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    userViewModel: UserViewModel,
    onProfileClicked: () -> Unit,
    onLoginClicked: () -> Unit
) {
    val loginState by userViewModel.loginState.collectAsState() // ViewModel StateFlow

//    loginState.apply {
//        Logger.d(TAG,"LoginState: $this")
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Home Screen")

        Button(
            onClick = { onProfileClicked() },
            enabled = loginState.isLoginSuccessful
        ) {
            Text("Go to Profile")
        }

        if (!loginState.isLoginSuccessful) {
            Button(onClick = { onLoginClicked() }) {
                Text("Go to Login")
            }
        }
    }
}