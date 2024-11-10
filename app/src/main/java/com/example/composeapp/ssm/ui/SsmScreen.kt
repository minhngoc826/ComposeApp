package com.example.composeapp.ssm.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.common.Logger
import com.example.composeapp.common.MyTopAppBar
import com.example.composeapp.common.SubScreen
import com.example.composeapp.ssm.ui.screen.ConnectionScreen
import com.example.composeapp.ssm.ui.screen.MainScreen
import com.example.composeapp.ssm.ui.screen.MenuScreen
import com.example.composeapp.ssm.ui.screen.SearchingScreen
import com.example.composeapp.ssm.ui.screen.SelectOsScreen
import com.example.composeapp.ssm.viewmodel.SsmViewModel
import com.example.composeapp.theme.ComposeAppTheme

/* Navigation-Compose
* 1. NavController:     redirect composable (screen) to destination
* 2. NavGraph:          maps composable to navigation destination
* 3. NavHost:           show destination current of NavGraph
* */

@Composable
fun SsmScreen(
    viewModel: SsmViewModel = viewModel(SsmViewModel::class.java),
    navHostController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val curScreen = SubScreen.valueOf(navBackStackEntry?.destination?.route ?: SubScreen.SSM_MAIN.name)

    Scaffold(
        topBar = {
            MyTopAppBar(
                curScreen = curScreen,
                canNavigateUp = navHostController.previousBackStackEntry != null,
                modifier = Modifier.fillMaxWidth(),
                navigateUp = {
                    Logger.d(curScreen.name, "navigateUp: curScreen: $curScreen, size: ${navHostController.visibleEntries.value.size}, visibleEntries: ${navHostController.visibleEntries}")
                    navHostController.visibleEntries.value.apply {
                        Logger.d(curScreen.name, "entries: $this")
                    }
                    navHostController.navigateUp()
//                    navHostController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        val ssmUiState by viewModel.ssmUiState.collectAsState()
        println("uiState: $ssmUiState")

        NavHost(
            navController = navHostController,
            startDestination = SubScreen.SSM_MAIN.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = SubScreen.SSM_MAIN.name) {
                MainScreen(
                    onNewActivityClicked = { navHostController.navigate(SubScreen.SSM_NEW.name) },
                    onMenuClicked = { navHostController.navigate(SubScreen.SSM_MENU.name) },
                    onSenderBtnClicked = { navHostController.navigate(SubScreen.SSM_CONNECTION.name) },
                    onReceiverBtnClicked = { navHostController.navigate(SubScreen.SSM_SELECT_OS.name) }
                )
            }

            composable(route = SubScreen.SSM_CONNECTION.name) {
                ConnectionScreen(
                    onWirelessTypeClicked = { navHostController.navigate(SubScreen.SSM_SEARCHING.name) },
                    onCableTypeClicked = { navHostController.navigate(SubScreen.SSM_SEARCHING.name) }
                )
            }

            composable(route = SubScreen.SSM_SELECT_OS.name) {
                SelectOsScreen(
                    onAndroidTypeClicked = { navHostController.navigate(SubScreen.SSM_SEARCHING.name) },
                    onIphoneTypeClicked = { navHostController.navigate(SubScreen.SSM_SEARCHING.name) }
                )
            }

            composable(route = SubScreen.SSM_SEARCHING.name) {
                SearchingScreen()
            }

            dialog(route = SubScreen.SSM_MENU.name) {
                MenuScreen()
            }

            activity(route = SubScreen.SSM_NEW.name) {
                label = "ActivityScreen"
                activityClass = SsmNewActivity::class
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SsmScreenPreview() {
    ComposeAppTheme {
        SsmScreen()
    }
}