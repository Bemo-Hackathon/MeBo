package com.example.bemo.data.models

data class ChatGPTRequest(
    val model: String,
    val messages: List<Message>,
    val max_tokens: Int
) {
    data class Message(
        val role: String, // "system", "user", or "assistant"
        val content: String
    )
}


data class ChatGPTResponse(
    val id: String,
    val choices: List<Choice>
)

data class Choice(
    val message: ChatGPTRequest.Message
)