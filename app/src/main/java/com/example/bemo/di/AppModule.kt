package com.example.bemo.di

import com.example.bemo.data.remote.MyApi
import com.example.bemo.data.repository.MyRepositoryImpl
import com.example.bemo.domain.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.openai.com/" // URL base da API

    @Provides
    @Singleton
    fun provideMyApi(): MyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
            .create(MyApi::class.java)
    }
}