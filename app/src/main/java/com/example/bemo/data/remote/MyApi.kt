package com.example.bemo.data.remote

import retrofit2.http.GET

interface MyApi {
    @GET
    suspend fun sendMessage(message: String)
}