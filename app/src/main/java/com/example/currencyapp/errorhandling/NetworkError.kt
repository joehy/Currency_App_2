package com.example.currencyapp.errorhandling

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkError(
    val status: Int = -1,
    @Expose
    @SerializedName("statusCode")
    val statusCode: String = "",
    @Expose
    @SerializedName("message")
    val message: String = ""
)