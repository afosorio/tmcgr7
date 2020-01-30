package com.grupo7.moneychange.data.network

import retrofit2.Response
import java.io.IOException

sealed class ResultData<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultData<T>()
    data class Error(val exception: Exception) : ResultData<Nothing>()
}

suspend fun <T : Any> safeApiCall(call: suspend () -> ResultData<T>, errorMessage: String): ResultData<T> = try {
    call.invoke()
} catch (e: Exception) {
    ResultData.Error(IOException(errorMessage, e))
}

fun <T : Any> Response<T>.callServices(): ResultData<T> {
    if (this.isSuccessful) {
        return if (this.body() != null) {
            ResultData.Success(this.body() as T)
        } else {
            ResultData.Error(Exception(this.errorBody().toString()))
        }
    }
    return ResultData.Error(IOException())
}