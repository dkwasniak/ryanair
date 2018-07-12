package com.damiankwasniak.ryanair.di.module

import com.damiankwasniak.ryanair.network.FlightsApiService
import com.damiankwasniak.ryanair.network.StationsApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.litlab.kidappolis.di.qualifiers.FlightsRetrofitClient
import org.litlab.kidappolis.di.qualifiers.StationsRetrofitClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by damiankwasniak on 09.04.2018.
 */
@Module
class ApiModule(private val stationsBaseUrl: String, private val flightsBaseUrl: String) {

    @Provides
    @Singleton
    @StationsRetrofitClient
    fun provideStationsRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(stationsBaseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Singleton
    @FlightsRetrofitClient
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(flightsBaseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Singleton
    fun provideStationsApiService(@StationsRetrofitClient retrofit: Retrofit): StationsApiService {
        return retrofit.create(StationsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFlightsApiService(@FlightsRetrofitClient retrofit: Retrofit): FlightsApiService {
        return retrofit.create(FlightsApiService::class.java)
    }
}