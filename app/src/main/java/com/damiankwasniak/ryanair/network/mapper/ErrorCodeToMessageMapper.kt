package com.damiankwasniak.ryanair.network.mapper

import android.content.Context
import com.damiankwasniak.ryanair.R
import com.damiankwasniak.ryanair.network.error.ApiErrorCodes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorCodeToMessageMapper @Inject constructor(context: Context) {

    private val errorMessagesMap: Map<Int, String>

    private val unknownApiError: String

    init {
        val resources = context.resources
        unknownApiError = resources.getString(R.string.general_critical_error)
        errorMessagesMap = mapOf(
                ApiErrorCodes.NO_INTERNET_CONNECTION to resources.getString(R.string.general_network_not_available),
                ApiErrorCodes.UNKNOWN to unknownApiError
        )
    }

    fun getErrorMsg(apiCode: Int): String = errorMessagesMap[apiCode] ?: unknownApiError

}