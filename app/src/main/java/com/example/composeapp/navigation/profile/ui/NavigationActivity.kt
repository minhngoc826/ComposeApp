package com.example.composeapp.navigation.profile.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.common.Logger
import com.example.composeapp.common.MyTopAppBar
import com.example.composeapp.common.SubScreen
import com.example.composeapp.navigation.profile.repos.UserRepository
import com.example.composeapp.navigation.profile.ui.screen.HomeScreen
import com.example.composeapp.navigation.profile.ui.screen.LoginScreen
import com.example.composeapp.navigation.profile.ui.screen.ProfileScreen
import com.example.composeapp.navigation.profile.viewmodel.UserViewModel
import com.example.composeapp.theme.ComposeAppTheme

class NavigationActivity : ComponentActivity() {
    companion object {
        const val TAG = "NavigationActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                NavigationScreen()
            }
        }
    }
}

@SuppressLint("RestrictedApi")
@Composable
fun NavigationScreen(
    viewModel: UserViewModel = viewModel(UserViewModel::class.java),
//    viewModel: UserViewModel = UserViewModel(UserRepository()),
    navHostController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val curScreen = SubScreen.valueOf(navBackStackEntry?.destination?.route ?: SubScreen.NAV_HOME.name)

    Scaffold(
        topBar = {
            MyTopAppBar(
                curScreen = curScreen,
                canNavigateUp = navHostController.previousBackStackEntry != null,
                modifier = Modifier
                    .fillMaxWidth()
                ,
                navigateUp = {
                    Logger.d(NavigationActivity.TAG, "navigateUp: backStack, size=" + navHostController.currentBackStack.value.size)
                    navHostController.currentBackStack.value.map {
                        Logger.d(NavigationActivity.TAG, "navigateUp: navBackStackEntry: ${it.destination.route}")
                    }
//                    navHostController.popBackStack()
                    navHostController.navigateUp()
                }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navHostController,
            startDestination = SubScreen.NAV_HOME.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = SubScreen.NAV_HOME.name) {
                HomeScreen(
                    userViewModel = viewModel,
                    onProfileClicked = { navHostController.navigate(SubScreen.NAV_PROFILE.name) },
                    onLoginClicked = { navHostController.navigate(SubScreen.NAV_LOGIN.name) }
                )
            }

            composable(route = SubScreen.NAV_PROFILE.name) {
                ProfileScreen(
                    userViewModel = viewModel,
                    needToLoginAction = { navHostController.navigate(SubScreen.NAV_LOGIN.name) }
                )
            }

            composable(route = SubScreen.NAV_LOGIN.name) {
                LoginScreen(
                    userViewModel = viewModel,
                    doLogin = { userName: String, password: String -> viewModel.doLogin(userName, password) },
                    onLoginSuccess =
                    {
                        Logger.d(NavigationActivity.TAG, "onLoginSuccess: backStack, size=" + navHostController.currentBackStack.value.size)
                        navHostController.currentBackStack.value.map {
                            Logger.d(NavigationActivity.TAG, "onLoginSuccess: navBackStackEntry: ${it.destination.route}")
                        }
//                        navHostController.popBackStack(route = SubScreen.NAV_HOME.name, inclusive = true, saveState = true)
                        navHostController.popBackStack()                        // pop Login screen
                        navHostController.navigate(SubScreen.NAV_PROFILE.name)
                    },
                    onLoginFailed = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationScreenPreview() {
    ComposeAppTheme {
        NavigationScreen()
    }
}