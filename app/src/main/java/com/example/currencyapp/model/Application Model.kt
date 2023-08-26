package com.example.currencyapp.model

import android.app.Application
import com.example.currencyapp.BuildConfig
import com.example.currencyapp.remote.NetworkHelper
import com.example.currencyapp.remote.NetworkService
import com.example.currencyapp.remote.Networking
import com.example.currencyapp.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModel {
    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL,
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(application: Application): NetworkHelper = NetworkHelper(application)



    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return object : SchedulerProvider {
            override fun computation(): Scheduler = Schedulers.computation()
            override fun io(): Scheduler = Schedulers.io()
            override fun ui(): Scheduler = AndroidSchedulers.mainThread()
        }
    }
}