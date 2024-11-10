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

private const val TAG = "ProfileScreen"

@Composable
fun ProfileScreen(
    userViewModel: UserViewModel,
    needToLoginAction: () -> Unit
) {
    val userInfo by userViewModel.userInfo.collectAsState()
    val loginState by userViewModel.loginState.collectAsState()

//    loginState.apply {
//        Logger.d(TAG,"LoginState: $this")
//    }
//    userInfo.apply {
//        Logger.d(TAG,"UserInfo: $this")
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (loginState.isLoginSuccessful) {
            Text(text = "Profile")
            Text(text = "Name: ${userInfo.name}")
            Text(text = "Age: ${userInfo.age}")
        } else {
            Button(onClick = { needToLoginAction() }) {
                Text(text = "Need to Login")
            }
        }
    }

}
