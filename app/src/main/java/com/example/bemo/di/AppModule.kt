package com.example.bemo.di

import com.example.bemo.BuildConfig
import com.example.bemo.data.remote.CustomerApi
import com.example.bemo.data.remote.MyApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.openai.com/" // URL base da API
    private const val COSTUMER_BASE_URL = "https://deploy-api-hacka.onrender.com/" // URL base da API

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        "Bearer ${BuildConfig.apiKeySafe}"
                    ) // Use a API key do BuildConfig
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideMyApi(okHttpClient: OkHttpClient): MyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCostumerApi() : CustomerApi {
        return Retrofit.Builder()
            .baseUrl(COSTUMER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CustomerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebase(): FirebaseAuth{
        return Firebase.auth
    }
}