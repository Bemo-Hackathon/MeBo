package com.example.bemo.data.models

import com.google.gson.annotations.SerializedName

data class CustomerRequest(
    @SerializedName("user_input")
    val input: String,
    @SerializedName("customer_data")
    val customer: Customer
) {
    data class Customer(
        @SerializedName("Nome")
        val name: String,
        @SerializedName("Idade")
        val age: Int,
        @SerializedName("SeniorCitizen")
        val seniorCitizen: Boolean,
        @SerializedName("gender")
        val gender: String
    )
}

data class CustomerResponse(
    @SerializedName("response")
    val response: String
)
