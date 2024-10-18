package com.example.bemo.domain.repository

import com.example.bemo.data.models.ChatGPTRequest
import com.example.bemo.data.models.ChatGPTResponse
import com.example.bemo.data.models.CustomerRequest
import com.example.bemo.data.models.CustomerResponse

interface MyRepository {

    suspend fun sendCustomerMessage(request: CustomerRequest): CustomerResponse

    suspend fun sendMessage(request: ChatGPTRequest): ChatGPTResponse
}