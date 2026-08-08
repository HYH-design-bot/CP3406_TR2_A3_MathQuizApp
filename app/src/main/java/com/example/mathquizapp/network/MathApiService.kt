package com.example.mathquizapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MathApiService {
    @GET("/jokes/random?category=nerdy")
    suspend fun getRandomMathFact(): MathFactResponse

    companion object {
        private const val BASE_URL = "https://api.chucknorris.io"

        fun create(): MathApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MathApiService::class.java)
        }
    }
}