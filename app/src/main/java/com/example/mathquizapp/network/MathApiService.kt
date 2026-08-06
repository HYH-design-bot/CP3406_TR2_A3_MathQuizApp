package com.example.mathquizapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface MathApiService {
    @Headers("Content-Type: application/json")
    @GET("random/math?json")
    suspend fun getRandomMathFact(): MathFactResponse

    companion object {
        private const val BASE_URL = "https://numbersapi.com"

        fun create(): MathApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MathApiService::class.java)
        }
    }
}