package com.example.bemo.presentation.ui.states

data class ChatUiState(
    val messages: MutableList<String> = mutableListOf(),
    var paymentResponse: String? = null
)
