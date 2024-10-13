package com.example.bemo

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bemo.data.models.ChatGPTRequest
import com.example.bemo.data.models.Message
import com.example.bemo.domain.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val myRepository: MyRepository
) : ViewModel() {


    var chatMessages = mutableStateListOf<String>()
        private set

    fun sendMessage(userInput: String) {
        chatMessages.add("User: $userInput")

        viewModelScope.launch {
            val request = ChatGPTRequest(
                model = "gpt-3.5-turbo",
                messages = listOf(
                    Message(role = "system", content = "Voce e um assistente de um app financeiro"),
                    Message(role = "user", content = userInput)
                ),
                maxCompletionTokens = 50
            )

            try {
                val response = myRepository.sendMessage(request)
                // Add the assistant's response to the chat
                chatMessages.add("Be'Mo: ${response.choices.firstOrNull()?.message?.content ?: "No response"}")
            } catch (e: Exception) {
                chatMessages.add("Error: ${e.localizedMessage}")
                Log.e("ChatViewModel", "Erro ao enviar mensagem: ${e.message}")

            }
        }
    }
}