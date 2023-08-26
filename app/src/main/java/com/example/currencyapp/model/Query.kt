package com.example.currencyapp.model

import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("from") val from : Boolean,
    @SerializedName("to") val to : String,
    @SerializedName("amount") val amount : String,
)
