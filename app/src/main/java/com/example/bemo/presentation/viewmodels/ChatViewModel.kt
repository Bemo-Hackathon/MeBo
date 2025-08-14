package com.example.bemo.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bemo.authentication.FirebaseAuthAPI
import com.example.bemo.data.models.ChatGPTRequest
import com.example.bemo.data.models.CustomerRequest
import com.example.bemo.data.models.Message
import com.example.bemo.data.models.PaymentRequest
import com.example.bemo.domain.repository.MyRepository
import com.example.bemo.presentation.ui.states.ChatUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val myRepository: MyRepository,
    private val firebaseAuthRepository: FirebaseAuthAPI,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    var uiState = _uiState.asStateFlow()

    fun sendPaymentStatus() {
        viewModelScope.launch {
            val request = PaymentRequest(
                customerID = "12345",
                nome = "Gabriel",
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
                _uiState.update { currentState ->
                    currentState.copy(
                        paymentResponse = "Error: ${e.localizedMessage}"
                    )
                }
                Log.e("ChatViewModel", "Erro ao enviar mensagem: ${e.message}")
            }
        }

    }


    fun sendCustomerMessage(userInput: String) {
        _uiState.update { currentState ->
            currentState.copy(
                messages = currentState.messages.toMutableList().also {
                    it.add("User: $userInput")
                }
            )
        }

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
                _uiState.update { currentState ->
                    currentState.copy(
                        messages = currentState.messages.toMutableList().also {
                            it.add("BeMo: ${response.response}")
                        }
                    )
                }
                Log.d("ChatViewModel", "Resposta recebida: ${response.response}")

            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        messages = currentState.messages.toMutableList().also {
                            it.add("Error: ${e.localizedMessage}")
                        }
                    )
                }
            }
        }
    }

    fun signOut() {
        firebaseAuthRepository.signOut()
    }

    fun sendMessage(userInput: String) {
        val chatMessages = _uiState.value.messages
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
                _uiState.update { currentState ->
                    currentState.copy(
                        paymentResponse = response.choices.firstOrNull()?.message?.content
                    )
                }
            } catch (e: Exception) {
                chatMessages.add("Error: ${e.localizedMessage}")
                Log.e("ChatViewModel", "Erro ao enviar mensagem: ${e.message}")

            }
        }
    }
}
