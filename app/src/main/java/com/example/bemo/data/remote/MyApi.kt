package com.example.bemo.data.remote

import com.example.bemo.BuildConfig
import com.example.bemo.data.models.ChatGPTRequest
import com.example.bemo.data.models.ChatGPTResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface MyApi {

    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    suspend fun sendMessage(
        @Body request: ChatGPTRequest
    ): ChatGPTResponse
}