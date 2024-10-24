package com.example.bemo.domain.repository

import com.example.bemo.data.models.ChatGPTRequest
import com.example.bemo.data.models.ChatGPTResponse
import com.example.bemo.data.models.CustomerRequest
import com.example.bemo.data.models.CustomerResponse
import com.example.bemo.data.models.OfferRequest
import com.example.bemo.data.models.OfferResponse
import com.example.bemo.data.models.PaymentRequest
import com.example.bemo.data.models.PaymentResponse

interface MyRepository {

    suspend fun sendCustomerMessage(request: CustomerRequest): CustomerResponse

    suspend fun sendMessage(request: ChatGPTRequest): ChatGPTResponse

    suspend fun sendPaymentStatus(request: PaymentRequest): PaymentResponse

    suspend fun sendOffer(request: OfferRequest): OfferResponse
}