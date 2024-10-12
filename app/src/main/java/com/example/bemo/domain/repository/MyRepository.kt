package com.example.bemo.domain.repository

import com.example.bemo.data.models.ChatGPTResponse

interface MyRepository{

    suspend fun sendMessage(message: String): ChatGPTResponse
}