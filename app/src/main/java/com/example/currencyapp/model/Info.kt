package com.example.currencyapp.model

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("timestamp") val timestamp : String,
    @SerializedName("rate") val date : String,
)
