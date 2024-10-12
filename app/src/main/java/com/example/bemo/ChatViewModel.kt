package com.example.bemo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bemo.domain.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val myRepository: MyRepository
) : ViewModel() {


    // List to hold chat messages (both from the user and the assistant)
    var chatMessages = mutableStateListOf<String>()
        private set

    fun sendMessage(userMessage: String) {
        // Add the user's message to the list
        chatMessages.add("User: $userMessage")

        // Send the message to the API in a coroutine
        viewModelScope.launch {
            try {
                val response = myRepository.sendMessage(userMessage)
                // Add the assistant's response to the chat
                chatMessages.add("Assistant: ${response.choices.firstOrNull()?.message?.content ?: "No response"}")
            } catch (e: Exception) {
                chatMessages.add("Error: ${e.localizedMessage}")
            }
        }
    }
}