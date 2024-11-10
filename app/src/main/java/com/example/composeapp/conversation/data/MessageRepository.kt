package com.example.composeapp.conversation.data

class MessageRepository(private val messageDataSource: MessageDataSource) {
    fun getListMessages(channelId: String) = messageDataSource.getListMessages(channelId)
    fun getLatestMessages(channelId: String) = messageDataSource.getLatestMessages(channelId)
}