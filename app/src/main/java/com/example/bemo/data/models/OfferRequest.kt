package com.example.bemo.data.models

import com.google.gson.annotations.SerializedName


data class OfferRequest(
    @SerializedName("customerID")
    val customerId: String,
    @SerializedName("Nome")
    val nome: String,
    @SerializedName("Idade")
    val idade: Int,
    @SerializedName("RendaReais")
    val rendaReais: Int,
    @SerializedName("Partner")
    val partner: String,
    @SerializedName("Dependents")
    val dependents: Int,
    @SerializedName("PhoneService")
    val phoneService: String,
    @SerializedName("InternetService")
    val internetService: String,
    @SerializedName("MonthlyCharges")
    val monthlyCharges: Int,
    @SerializedName("DeviceProtection")
    val deviceProtection: String,
    @SerializedName("StreamingTV")
    val streamingTV: String,
    @SerializedName("StreamingMovies")
    val streamingMovies: String,
    @SerializedName("Contract")
    val contract: String,
    @SerializedName("PaymentMethod")
    val paymentMethod: String,
    @SerializedName("TotalCharges")
    val totalCharges: Int,
    @SerializedName("TVUsageHours")
    val tvUsageHours: Int,
    @SerializedName("InternetUsageGB")
    val internetUsageGB: Int,
    @SerializedName("PhoneUsageHours")
    val phoneUsageHours: Int,
    @SerializedName("PreviousPurchases")
    val previousPurchases: List<String>
)

data class OfferResponse(
    @SerializedName("offer")
    val response: String
)


