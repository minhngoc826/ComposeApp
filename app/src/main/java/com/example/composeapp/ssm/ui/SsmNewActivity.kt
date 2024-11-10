package com.example.composeapp.ssm.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.enableSavedStateHandles
import com.example.composeapp.theme.ComposeAppTheme

class SsmNewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableSavedStateHandles()
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAppTheme {
                NewScreen()
            }
        }
    }
}

@Composable
fun NewScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "New Activity Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun NewScreenPreview() {
    ComposeAppTheme {
        NewScreen()
    }
}