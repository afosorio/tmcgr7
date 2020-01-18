package com.grupo7.moneychange.data.network

import retrofit2.Response
import java.io.IOException

sealed class RetrofitResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : RetrofitResult<T>()
    data class Error(val exception: Exception) : RetrofitResult<Nothing>()
}

suspend fun <T : Any> safeApiCall(call: suspend () -> RetrofitResult<T>, errorMessage: String): RetrofitResult<T> = try {
    call.invoke()
} catch (e: Exception) {
    RetrofitResult.Error(IOException(errorMessage, e))
}

fun <T : Any> Response<T>.callServices(): RetrofitResult<T> {
    if (this.isSuccessful) {
        return if (this.body() != null) {
            RetrofitResult.Success(this.body() as T)
        } else {
            RetrofitResult.Error(IOException())
        }
    }
    return RetrofitResult.Error(IOException())
}