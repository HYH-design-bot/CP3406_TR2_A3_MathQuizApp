package com.example.mathquizapp.network

import com.google.gson.annotations.SerializedName

data class MathFactResponse(
    @SerializedName("value")
    val factText: String
)