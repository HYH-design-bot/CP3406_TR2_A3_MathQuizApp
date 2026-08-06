package com.example.mathquizapp.network

import com.google.gson.annotations.SerializedName

data class MathFactResponse(
    @SerializedName("text") val factText: String,
    @SerializedName("number") val number: Int,
    @SerializedName("found") val found: Boolean,
    @SerializedName("type") val type: String
)