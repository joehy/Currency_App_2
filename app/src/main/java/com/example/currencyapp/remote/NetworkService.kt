package com.example.currencyapp.remote

import com.crave.store.data.model.login.Data
import com.example.currencyapp.remote.data.ConvertRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NetworkService {
    @POST(Endpoints.CONVERT)
    fun doConvertCall(
        @Body request: ConvertRequest,
    ): Single<Data>
    @GET(Endpoints.CONVERT)
    fun doGetConvertCall(
        @Query("access_key") access_key: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: String
    ): Single<Data>


}