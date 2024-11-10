package com.example.composeapp.conversation.data

import com.example.composeapp.conversation.viewmodel.Message

abstract class MessageDataSource {
    abstract fun getListMessages(channelId: String): List<Message>
    abstract fun getLatestMessages(channelId: String): List<Message>
}