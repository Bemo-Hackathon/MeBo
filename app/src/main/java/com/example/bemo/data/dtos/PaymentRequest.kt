package com.example.bemo.data.models

import com.google.gson.annotations.SerializedName

data class PaymentRequest(
    @SerializedName("costumerID")
    val customerID: String,
    @SerializedName("Nome")
    val nome: String,
    @SerializedName("PaymentMethod")
    val paymentMethod: String,
    @SerializedName("CardExpiryDate")
    val cardExpiryDate: String,
    @SerializedName("LastPaymentDate")
    val lastPaymentDate: String,
    @SerializedName("SubscriptionStatus")
    val subscriptionStatus: String,
    @SerializedName("FraudSuspected")
    val fraudSuspected: String
)

data class PaymentResponse(
    @SerializedName("payment_notification")
    val response: String
)
