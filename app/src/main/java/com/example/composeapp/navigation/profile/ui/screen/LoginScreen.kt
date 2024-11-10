package com.example.composeapp.navigation.profile.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeapp.common.Logger
import com.example.composeapp.navigation.profile.viewmodel.UserViewModel

private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(
    userViewModel: UserViewModel,
    doLogin: (String, String) -> Unit,
    onLoginSuccess: () -> Unit,
    onLoginFailed: () -> Unit
) {
    val loginState by userViewModel.loginState.collectAsState()

//    Logger.d(TAG,"start")
//    loginState.apply {
//        Logger.d(TAG,"LoginState: $this")
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "User name: Ngoc")
        Text(text = "Password: 123456")
        if (loginState.reasonMsg.isNotEmpty()) {
            Text("Message: ${loginState.reasonMsg}")
        }

        if (loginState.isLoginSuccessful) {
            Button(onClick = { onLoginSuccess() }) {
                Text(text = "Go to home")
            }
        } else {
            Button(onClick = { doLogin("Ngoc", "123456") }) {
                Text("Login")
            }
        }

        if (loginState.isLoading) {
            CircularProgressIndicator()
            Text(text = "Waiting for logging in")
        }

        if (loginState.isLoginSuccessful) onLoginSuccess()
    }
}