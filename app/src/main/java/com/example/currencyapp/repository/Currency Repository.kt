package com.example.currencyapp.repository

import com.crave.store.data.model.login.Data
import com.example.currencyapp.BuildConfig
import com.example.currencyapp.model.Info
import com.example.currencyapp.model.Query
import com.example.currencyapp.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val networkService: NetworkService){
    fun doConvertCall(from: String, to: String, amount: String)
    : Single<Data> =
        networkService.doGetConvertCall(
                BuildConfig.API_KEY,
                from,
                to,
                amount,
            ).map {
                Data(
                    it.success,
                    it.date,
                    it.result,
                    it.historical,
                    Query(
                        it.query.from,
                        it.query.to,
                        it.query.amount,
                    ),
                    Info(
                        it.info.timestamp,
                        it.info.date,
                    )
                )
            }


}