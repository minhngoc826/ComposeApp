package com.example.composeapp.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapp.R
import com.example.composeapp.theme.ComposeAppTheme

class BasicActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                BasicScreen()  // BasicScreen is a composable function that will be called when this activity is created.
            }
        }
    }
}

@Composable
fun BasicScreen() {
    Text(text = stringResource(id = R.string.app_basic_screen))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme { BasicScreen() }
}