package com.example.bemo.data.repository


import com.example.bemo.data.models.ChatGPTRequest
import com.example.bemo.data.models.ChatGPTResponse
import com.example.bemo.data.remote.MyApi
import com.example.bemo.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myApi: MyApi,
) : MyRepository {

    override suspend fun sendMessage(request: ChatGPTRequest): ChatGPTResponse {
        return myApi.sendMessage(request)
    }
}