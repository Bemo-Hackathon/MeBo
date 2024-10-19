package com.example.bemo.ui.states

data class ChatUiState(
    val messages: MutableList<String> = mutableListOf(),
    var paymentResponse: String? = null
)
