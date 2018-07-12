package com.damiankwasniak.ryanair.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class HeadersInterceptor() : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder: Request.Builder

        builder = request
                .newBuilder()
                .addHeader(HeadersContract.CONTENT_TYPE, HeadersContract.APPLICATION_JSON)


        return chain.proceed(builder.build())
    }
}