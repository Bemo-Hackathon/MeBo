package com.example.bemo.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bemo.data.models.ChatGPTRequest
import com.example.bemo.data.models.CustomerRequest
import com.example.bemo.data.models.Message
import com.example.bemo.domain.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val myRepository: MyRepository,
) : ViewModel() {


    var chatMessages = mutableStateListOf<String>()
        private set

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    fun sendPaymentStatus() {
        viewModelScope.launch {
            val request = PaymentRequest(
                customerID = "12345",
                nome = "rafa",
                paymentMethod = "credit",
                cardExpiryDate = "16/12/2025",
                lastPaymentDate = "16/12/2025",
                subscriptionStatus = "inativo",
                fraudSuspected = "nao"
            )
            try {
                val response = myRepository.sendPaymentStatus(request = request)
                _uiState.update { currentState ->
                    currentState.copy(
                        paymentResponse = response.response
                    )
                }
            } catch (e: Exception) {
                chatMessages.add("Error: ${e.localizedMessage}")
                Log.e("ChatViewModel", "Erro ao enviar mensagem: ${e.message}")
            }
        }

    }


    fun sendCustomerMessage(userInput: String) {
        chatMessages.add("User: $userInput")

        viewModelScope.launch {
            val request = CustomerRequest(
                input = userInput,
                customer = CustomerRequest.Customer(
                    age = 30,
                    name = "Gabriel",
                    seniorCitizen = false,
                    gender = "Masculino"
                )
            )

            try {
                val response = myRepository.sendCustomerMessage(request = request)
                chatMessages.add("Be'Mo: ${response.response}")
            } catch (e: Exception) {
                chatMessages.add("Error: ${e.localizedMessage}")
                Log.e("ChatViewModel", "Erro ao enviar mensagem: ${e.message}")
            }
        }

    }

    fun sendMessage(userInput: String) {
        chatMessages.add("User: $userInput")

        viewModelScope.launch {
            val request = ChatGPTRequest(
                model = "gpt-3.5-turbo",
                messages = listOf(
                    Message(
                        role = "system",
                        content = "Voce e um assistente de um app financeiro, por favor so responda perguntas sobre financeiro por favor"
                    ),
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