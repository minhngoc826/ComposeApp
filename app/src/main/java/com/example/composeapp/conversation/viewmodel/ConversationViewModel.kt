package com.example.composeapp.conversation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composeapp.conversation.data.LocalMessageDataSourceImpl
import com.example.composeapp.conversation.data.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConversationViewModel(
    channelId: String = "1234",
    messageRepository: MessageRepository = MessageRepository(LocalMessageDataSourceImpl())
) : ViewModel() {

    private val _messages = MutableStateFlow(messageRepository.getListMessages(channelId))
    val messages = _messages.asStateFlow() // TODO: 2021-08-09 replace with stateIn when it's available in compose

    fun sendMessage(message: Message) {

    }
}
