package com.example.composeapp.conversation.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.composeapp.common.Logger
import com.example.composeapp.conversation.data.LocalMessageDataSourceImpl
import com.example.composeapp.conversation.data.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConversationViewModel(
    channelId: String = "1234",
    messageRepository: MessageRepository = MessageRepository(LocalMessageDataSourceImpl())
) : ViewModel() {

    // way 1: can not add new message / delete message
//    private var _messages = MutableStateFlow(messageRepository.getListMessages(channelId))
//    var messages = _messages.asStateFlow() // TODO: 2021-08-09 replace with stateIn when it's available in compose
//
//
//    fun sendMessage(message: Message) {
//        Logger.d("ViewModel", "sendMessage $message")
//        _messages.value.
//        messages.value.as
//    }

    // way 2
    private val _messages = messageRepository.getLatestMessages(channelId).toMutableStateList() // mutableListOf<>().append { addAll() }
    val messages: List<Message>
        get() = _messages

    fun sendMessage(message: Message) {
        Logger.d("ViewModel", "sendMessage $message")
        _messages.add(message)
    }
}
