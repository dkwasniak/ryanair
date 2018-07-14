package com.damiankwasniak.ryanair.network

import com.damiankwasniak.ryanair.kidappolisApplication
import com.damiankwasniak.ryanair.network.error.ApiErrorCodes
import com.damiankwasniak.ryanair.network.mapper.ApiError

open class BaseService {

    fun handleApiError(err: ApiError, defaulf: () -> Unit) {
        when (err.apiCode) {
            ApiErrorCodes.NO_INTERNET_CONNECTION -> {
                kidappolisApplication.onNoInternetConnection()
            } else -> defaulf()
        }
    }
}