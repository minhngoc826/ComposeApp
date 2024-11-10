package com.example.composeapp.conversation.data

//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import com.example.composeapp.conversation.data.db.AppDatabase
//import com.example.composeapp.conversation.data.db.ConversationDao
//import com.example.composeapp.conversation.data.db.MessageDao
import com.example.composeapp.conversation.viewmodel.Message
import kotlinx.coroutines.flow.StateFlow

class LocalMessageDataSourceImpl : MessageDataSource() {
//    lateinit var messageDao: MessageDao
//    lateinit var conversationDao: ConversationDao
//    lateinit var appDb: AppDatabase

    override fun getListMessages(channelId: String): List<Message> {
        var messages = listOf<Message>()
        for (i in 10..30) {
            messages += Message(
                channelId = i.toString(),
                content = "Hello $i",
                timestamp = "10:$i"
            )
        }
        return messages;
    }

    override fun getLatestMessages(channelId: String): List<Message> {
        var messages = listOf<Message>()
        for (i in 10..30) {
            messages += Message(
                channelId = i.toString(),
                content = "Hello $i",
                timestamp = "10:$i"
            )
        }
        return messages;
    }
}