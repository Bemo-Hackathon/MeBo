package com.example.bemo.data.remote

import com.example.bemo.data.models.CustomerRequest
import com.example.bemo.data.models.CustomerResponse
import com.example.bemo.data.models.PaymentRequest
import com.example.bemo.data.models.PaymentResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CustomerApi {

    @POST("api/chat")
    suspend fun sendCustomerMessage(@Body request: CustomerRequest): CustomerResponse

    @POST("api/payment-status")
    suspend fun sendPaymentStatus(@Body request: PaymentRequest): PaymentResponse
}