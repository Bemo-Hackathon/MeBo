package com.example.bemo.data.repository


import com.example.bemo.data.models.ChatGPTRequest
import com.example.bemo.data.models.ChatGPTResponse
import com.example.bemo.data.models.CustomerRequest
import com.example.bemo.data.models.CustomerResponse
import com.example.bemo.data.models.PaymentRequest
import com.example.bemo.data.models.PaymentResponse
import com.example.bemo.data.remote.CustomerApi
import com.example.bemo.data.remote.MyApi
import com.example.bemo.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myApi: MyApi,
    private val customerApi: CustomerApi
) : MyRepository {

    override suspend fun sendCustomerMessage(request: CustomerRequest): CustomerResponse {
        return customerApi.sendCustomerMessage(request)
    }

    override suspend fun sendMessage(request: ChatGPTRequest): ChatGPTResponse {
        return myApi.sendMessage(request)
    }

    override suspend fun sendPaymentStatus(request: PaymentRequest): PaymentResponse {
        return customerApi.sendPaymentStatus(request)
    }
}