package com.example.composeapp.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.R
import com.example.composeapp.basic.lazylist.BaseScreen
import com.example.composeapp.basic.lazylist.BasicLazyListScreen
import com.example.composeapp.common.MyTopAppBar
import com.example.composeapp.common.SubScreen
import com.example.composeapp.common.theme.ComposeAppTheme

class BasicActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAppTheme {
                BasicScreen()  // BasicScreen is a composable function that will be called when this activity is created.
            }
        }
    }
}

@Composable
fun BasicScreen(
    navHostController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val curScreen = SubScreen.valueOf(navBackStackEntry?.destination?.route ?: SubScreen.BASIC_LAZY_LIST.name)

    Scaffold(
        topBar = {
            MyTopAppBar(
                curScreen = curScreen,
                canNavigateUp = navHostController.previousBackStackEntry != null,
                navigateUp = { navHostController.navigateUp() },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = SubScreen.BASIC_LAZY_LIST.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = SubScreen.BASIC_LAZY_LIST.name) {
                BasicLazyListScreen()
            }

            composable(route = SubScreen.BASIC_BASIC.name) {
                BaseScreen()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme { BasicScreen() }
}