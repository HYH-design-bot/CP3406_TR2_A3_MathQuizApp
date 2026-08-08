package com.example.mathquizapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MathApiService {
    @Headers("Content-Type: application/json")
    @GET("{number}/math?json")
    suspend fun getRandomMathFact(@Path("number") number: Int): MathFactResponse

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