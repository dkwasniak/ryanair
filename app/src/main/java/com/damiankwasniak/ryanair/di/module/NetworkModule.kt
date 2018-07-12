package com.damiankwasniak.ryanair.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.litlab.kidappolis.di.qualifiers.BaseOkHttpClient
import org.litlab.kidappolis.di.qualifiers.OkHttpInterceptors
import org.litlab.kidappolis.di.qualifiers.OkHttpNetworkInterceptors
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    var CACHE_SIZE: Long = 10 * 1024 * 1024 // 10 MB

    private val TIMEOUT_LENGTH_IN_SECONDS: Long = 6

    @Provides
    @Singleton
    @BaseOkHttpClient
    fun provideBaseOkHttpClient(context: Context): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.connectTimeout(TIMEOUT_LENGTH_IN_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_LENGTH_IN_SECONDS, TimeUnit.SECONDS)
                .cache(Cache(context.cacheDir, CACHE_SIZE))

        return okHttpBuilder.build()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(@BaseOkHttpClient okHttpClient: OkHttpClient,
                            @OkHttpInterceptors interceptors: MutableList<Interceptor>,
                            @OkHttpNetworkInterceptors networkInterceptors: MutableList<Interceptor>): OkHttpClient {

        val okHttpBuilder = okHttpClient.newBuilder()

        for (interceptor in interceptors) {
            okHttpBuilder.addInterceptor(interceptor)
        }

        for (networkInterceptor in networkInterceptors) {
            okHttpBuilder.addNetworkInterceptor(networkInterceptor)
        }
        return okHttpBuilder.build()
    }

}
