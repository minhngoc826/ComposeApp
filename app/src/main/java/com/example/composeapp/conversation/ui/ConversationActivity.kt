package com.example.composeapp.conversation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.common.Logger
import com.example.composeapp.common.MyTopAppBar
import com.example.composeapp.common.SubScreen
import com.example.composeapp.conversation.viewmodel.ConversationViewModel
import com.example.composeapp.conversation.viewmodel.Message
import com.example.composeapp.common.theme.ComposeAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Date

class ConversationActivity : ComponentActivity() {
    companion object {
        const val TAG = "ConversationActivity"
    }

    private var messages = listOf<Message>() // empty list

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeAppTheme {
//                ConversationScreen(viewModel(ConversationViewModel::class.java), rememberNavController(), messages)
                ConversationScreen()
            }
        }
    }
}

// include State hoisting pattern
@Composable
fun ConversationScreen(
    conversationViewModel: ConversationViewModel = viewModel(),
    navHostController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val curScreen = SubScreen.valueOf(navBackStackEntry?.destination?.route ?: SubScreen.APP_BASIC.name)
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()     // State hoisted to the ConversationScreen
    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0 // keyboard show or hide
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            MyTopAppBar(
                curScreen = curScreen,
                canNavigateUp = navHostController.previousBackStackEntry != null,
                navigateUp = { navHostController.navigateUp() },
                modifier = Modifier
//                    .fillMaxWidth()
                    .background(color = androidx.compose.ui.graphics.Color.Blue)
//                    .wrapContentHeight()
            )
        },
        floatingActionButton = {
            JumpToBottom(
                onClicked = { scope.launch { lazyListState.scrollToItem(conversationViewModel.messages.size) } }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) { innerPadding ->
        Logger.d(ConversationActivity.TAG, "ConversationScreen: innerPadding = $innerPadding")
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .statusBarsPadding()
                .imePadding()                           // for soft keyboard show/hide
//                .padding(innerPadding)
//            .padding(start = 10.dp, end = 10.dp)
//            .background(color = androidx.compose.ui.graphics.Color.Gray)
//            .verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MessageList(
                modifier = Modifier
                    .weight(1f)
                    .imePadding()
                    .background(color = Color.LightGray)
                ,
                scope = scope,
                messages = conversationViewModel.messages,
                lazyListState = lazyListState
            )     // Reuse same state in MessageList

            Spacer(modifier = Modifier.fillMaxWidth())

            UserInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()
                    .background(color = Color.White)
//                    .wrapContentHeight()
//                .padding(10.dp)
                ,
                onMessageSent = {  text: String, time: String ->                        // Apply UI logic to lazyListState
                    val newMessage = Message(
                        channelId = (conversationViewModel.messages.size + 1).toString(),
                        content = text,
                        timestamp = time
                    )
                    conversationViewModel.sendMessage(newMessage)      // Add message to ViewModel's LiveData
                    if (isKeyboardVisible) {
                        keyboardController?.hide()
                    }

                    scope.launch { lazyListState.scrollToItem(conversationViewModel.messages.size) }
                }
            )
        }
    }
}

@Composable
fun MessageList(
    modifier: Modifier,
    scope: CoroutineScope,
    messages: List<Message>,
    lazyListState: LazyListState
) {
    // list mess
    LazyColumn(
        state = lazyListState,   // Pass hoisted state to LazyColumn
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        items(messages, key = { message -> message.channelId }) { item ->
            MessageItem(item)
        }
    }
}

@Composable
fun JumpToBottom(onClicked: () -> Unit) {
    FloatingActionButton(
        onClick = { onClicked() },
        modifier = Modifier.size(30.dp, 20.dp)
    ) {
        Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Jump to bottom") // decorative icon, no description needed
    }
}

@Composable
fun MessageItem(message: Message) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "${message.content}", fontSize = 18.sp)
        Text(text = "${message.timestamp}", fontSize = 12.sp)
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp
        )
    }
}

@Composable
fun UserInput(
    modifier: Modifier,
    onMessageSent: (String, String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
//            .background(color = androidx.compose.ui.graphics.Color.DarkGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        var text by rememberSaveable { mutableStateOf("") } // Remember a mutable state variable
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(text = "Enter your message...") },
//            colors = TextFieldDefaults.colors(),
            modifier = Modifier
                .fillMaxWidth(0.9f)
//                .clip(shape = RoundedCornerShape(30.dp))
                .background(color = androidx.compose.ui.graphics.Color.White)
        )
        IconButton(
            onClick = {
                val timestamp = Date(System.currentTimeMillis())
                onMessageSent(text, "" + timestamp.hours + ":" + timestamp.minutes)
                text = ""
                // check keyborad show/hide
            }
        ) {
            Icon(imageVector = Icons.Filled.Send, contentDescription = "Send message", tint = androidx.compose.ui.graphics.Color.Blue)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
        ConversationScreen()
    }
}