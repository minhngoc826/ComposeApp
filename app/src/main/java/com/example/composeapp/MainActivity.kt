package com.example.composeapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.basic.BasicActivity
import com.example.composeapp.conversation.ui.ConversationActivity
import com.example.composeapp.common.Logger
import com.example.composeapp.common.MyTopAppBar
import com.example.composeapp.common.SubScreen
import com.example.composeapp.navigation.profile.ui.NavigationActivity
import com.example.composeapp.ssm.ui.SsmActivity
import com.example.composeapp.common.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    var broadcastReceiver: BroadcastReceiver? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                AppScreen()
            }
        }

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Logger.d(TAG, "onReceive: $intent")
            }
        }

        val intentFilter = IntentFilter("com.samsung.android.intent.action.REQUEST_BACKUP_RADIO")

//        val permission = "com.wssnps.permission.COM_WSSNPS"
        val permission = "xxxx.PERMISSION_SMART_SWITCH"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            registerReceiver(broadcastReceiver, intentFilter, permission, null, RECEIVER_EXPORTED)
        else
            registerReceiver(broadcastReceiver, intentFilter, permission, null)
        Logger.d(TAG, "onCreate: registerReceiver")
    }

    override fun onDestroy() {
        super.onDestroy()
        broadcastReceiver?.let { unregisterReceiver(it) }
    }
}

@Composable
fun AppScreen(
//    viewModel: MainViewModel = viewModel(MainViewModel::class.java),
    navHostController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val curScreen = SubScreen.valueOf(navBackStackEntry?.destination?.route ?: SubScreen.APP_MAIN.name)

    Scaffold(
        topBar = { MyTopAppBar(
            curScreen = curScreen,
            canNavigateUp = navHostController.previousBackStackEntry != null,
            modifier = Modifier.fillMaxWidth(),
            navigateUp = {
//                navHostController.popBackStack()
                navHostController.navigateUp()
            }
        ) }
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = SubScreen.APP_MAIN.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = SubScreen.APP_MAIN.name) {
                MainScreen(navHostController)
            }

            activity(route = SubScreen.APP_SSM.name) {
                label = "SSM"
                activityClass = SsmActivity::class
            }

            activity(route = SubScreen.APP_NAVIGATION.name) {
                label = "Navigation"
                activityClass = NavigationActivity::class
            }

            activity(route = SubScreen.APP_BASIC.name) {
                label = "Basic"
                activityClass = BasicActivity::class
            }

            activity(route = SubScreen.APP_CONVERSATION.name) {
                label = "Conversation"
                activityClass = ConversationActivity::class
            }
        }
    }
}


@Composable
fun MainScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        var name by remember { mutableStateOf("") }

        Button(
            onClick = { navHostController.navigate(SubScreen.APP_SSM.name) },
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Text(text = stringResource(id = R.string.app_ssm_screen))
        }

        Button(
            onClick = { navHostController.navigate(SubScreen.APP_NAVIGATION.name) },
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Text(text = stringResource(id = R.string.app_navigation_screen))
        }

        Button(
            onClick = { navHostController.navigate(SubScreen.APP_BASIC.name) },
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Text(text = stringResource(id = R.string.app_basic_screen))
        }

        Button(
            onClick = { navHostController.navigate(SubScreen.APP_CONVERSATION.name) },
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Text(text = stringResource(id = R.string.app_conversation_screen))
        }

        // more
//        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
//        LaunchedEffect(key1 = "value") { }
    }
}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    ComposeAppTheme {
        AppScreen()
    }
}