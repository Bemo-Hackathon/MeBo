package com.example.bemo.data.models

import com.google.gson.annotations.SerializedName

data class ChatGPTRequest(
    @SerializedName("model")
    val model: String,
    @SerializedName("messages")
    val messages: List<Message>,
    @SerializedName("max_completion_tokens")
    val maxCompletionTokens: Int?
)

data class Message(
    @SerializedName("role")
    val role: String, // "system", "user", or "assistant"
    @SerializedName("content")
    val content: String
)

data class ChatGPTResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("choices")
    val choices: List<Choice>
) {
    data class Choice(
        @SerializedName("index")
        val index: Int,
        @SerializedName("message")
        val message: Message,
        @SerializedName("finish_reason")
        val finishReason: String
    )
}
