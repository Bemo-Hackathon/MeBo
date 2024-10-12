package com.example.bemo.domain.repository

interface MyRepository{

    suspend fun sendMessage(message: String)
}