package com.example.currencyapp.remote.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ConvertRequest(
    @Expose
    @SerializedName("access_key")
    var access_key: String,
    @Expose
    @SerializedName("from")
    var from: String,

    @Expose
    @SerializedName("to")
    var to: String,

    @Expose
    @SerializedName("amount")
    var amount: String,


)