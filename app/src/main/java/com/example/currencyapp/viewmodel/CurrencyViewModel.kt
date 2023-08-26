package com.example.currencyapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crave.store.data.model.login.Data
import com.example.currencyapp.R
import com.example.currencyapp.errorhandling.Resource
import com.example.currencyapp.model.Country
import com.example.currencyapp.remote.NetworkHelper
import com.example.currencyapp.repository.CurrencyRepository
import com.example.currencyapp.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

@HiltViewModel
class CurrencyViewModel  @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val networkHelper: NetworkHelper,
    protected val schedulerProvider: SchedulerProvider,

    ): ViewModel() {
    val convertedData: MutableLiveData<Data> = MutableLiveData()
    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
     val selectedFromCountry: MutableLiveData<Country> = MutableLiveData()
     val selectedToCountry: MutableLiveData<Country> = MutableLiveData()
    fun onConvert(from: String, to: String, amount: String){
        currencyRepository.doConvertCall(from, to,amount)
            .subscribeOn(schedulerProvider.io())
            .subscribe(
                {
                    convertedData.postValue(it)
                },
                {
                    handleNetworkError(it)
                }
            )
    }

    protected fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                when (status) {
                    0 -> messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        messageStringId.postValue(Resource.error(R.string.network_login_again))
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                        messageStringId.postValue(Resource.error(R.string.network_internal_error))
                    HttpsURLConnection.HTTP_UNAVAILABLE ->
                        messageStringId.postValue(Resource.error(R.string.network_server_not_available))

                }
            }
        }
}