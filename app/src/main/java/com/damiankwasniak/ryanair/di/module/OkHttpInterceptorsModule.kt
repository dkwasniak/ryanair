package com.damiankwasniak.ryanair.di.module

import android.util.Log
import com.damiankwasniak.ryanair.network.HeadersInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.litlab.kidappolis.di.qualifiers.OkHttpInterceptors
import org.litlab.kidappolis.di.qualifiers.OkHttpNetworkInterceptors
import java.util.*
import javax.inject.Singleton


@Module
class OkHttpInterceptorsModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor({ message -> Log.i("NETWORK_LOG", message) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    @OkHttpInterceptors
    fun provideOkHttpInterceptors(httpLoggingInterceptor: HttpLoggingInterceptor): MutableList<Interceptor> {
        return Arrays.asList<Interceptor>(httpLoggingInterceptor)
    }

    @Provides
    @Singleton
    @OkHttpNetworkInterceptors
    fun provideOkHttpNetworkInterceptors(headersInterceptor: HeadersInterceptor): MutableList<Interceptor> {
        return mutableListOf(headersInterceptor)
    }

    @Provides
    @Singleton
    fun provideHeadersInterceptor(): HeadersInterceptor {
        return HeadersInterceptor()
    }
}
