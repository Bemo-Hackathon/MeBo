package com.example.bemo.data.repository


import com.example.bemo.data.models.ChatGPTRequest
import com.example.bemo.data.models.ChatGPTResponse
import com.example.bemo.data.remote.MyApi
import com.example.bemo.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myApi: MyApi,
) : MyRepository {

    override suspend fun sendMessage(message: String): ChatGPTResponse {
        // Creating the request body
        val chatRequest = ChatGPTRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(
                // You can define different roles ("user", "assistant")
                ChatGPTRequest.Message(role = "user", content = message)
            ),
            max_tokens = 10000 // Adjust as needed
        )

        // Sending the request and returning the response
        return myApi.sendMessage(chatRequest)
    }
}