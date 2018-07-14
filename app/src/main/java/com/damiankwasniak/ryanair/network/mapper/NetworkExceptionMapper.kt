package com.damiankwasniak.ryanair.network.mapper

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import com.damiankwasniak.ryanair.network.error.ApiErrorCodes
import com.damiankwasniak.ryanair.network.error.NoConnectivityException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

data class ApiError(val statusCode: Int = ApiErrorCodes.UNKNOWN,
                    val apiCode: Int = ApiErrorCodes.UNKNOWN,
                    /** Raw isError errorMessage sent from backend */
                    val rawMessage: String = "",
                    val errorMessage: String = "") : Throwable()

@Singleton
class NetworkExceptionMapper @Inject constructor(
        private val errorCodeToMessageMapper: ErrorCodeToMessageMapper
) {

    companion object {
        private const val ERROR_BODY_CODE_KEY = "code"

        private const val ERROR_BODY_MESSAGE_KEY = "message"

    }

    fun mapToApiError(exception: Throwable): ApiError {
        return when (exception) {
            is HttpException -> parseHttpException(exception)
            is UnknownHostException -> getNoInternetConnectionException(exception)
            is IOException -> parseIoException(exception)
            else -> logAndReturnUnknownError(exception)
        }
    }

    fun getUnknownApiError(): ApiError = ApiError(
            errorMessage = errorCodeToMessageMapper.getErrorMsg(ApiErrorCodes.UNKNOWN))

    private fun logAndReturnUnknownError(exception: Throwable): ApiError {
        Log.e(this::class.java.simpleName, "Unknown Retrofit exception", exception)
        val message = errorCodeToMessageMapper.getErrorMsg(ApiErrorCodes.UNKNOWN)
        return ApiError(errorMessage = message)
    }

    private fun parseIoException(exception: IOException): ApiError {
        return when (exception) {
            is ConnectException -> getNoInternetConnectionException(exception)
            is SocketTimeoutException -> getNoInternetConnectionException(exception)
            else -> logAndReturnUnknownError(exception)
        }
    }

    private fun parseHttpException(exception: HttpException): ApiError {
        return try {
            val errorBody = JSONObject(exception.response().errorBody()?.string())
            val rawMessage = errorBody[ERROR_BODY_MESSAGE_KEY].toString()
            val apiCode = if (errorBody[ERROR_BODY_CODE_KEY] is Int) {
                Integer.parseInt(errorBody[ERROR_BODY_CODE_KEY].toString())
            } else {
                ApiErrorCodes.UNKNOWN
            }
            ApiError(exception.code(),
                    apiCode,
                    rawMessage,
                    errorCodeToMessageMapper.getErrorMsg(apiCode))
        } catch (e: JSONException) {
            Log.e(this::class.java.simpleName, "JSON parse exception", e)
            ApiError(exception.code(),
                    ApiErrorCodes.UNKNOWN,
                    errorMessage = errorCodeToMessageMapper.getErrorMsg(ApiErrorCodes.UNKNOWN))
        }
    }

    private fun getNoInternetConnectionException(exception: Throwable): ApiError {
        Log.e(this::class.java.simpleName, "No internet connection exception", exception)
        return ApiError(ApiErrorCodes.NO_INTERNET_CONNECTION,
                ApiErrorCodes.NO_INTERNET_CONNECTION,
                errorMessage = errorCodeToMessageMapper.getErrorMsg(ApiErrorCodes.NO_INTERNET_CONNECTION))
    }
}