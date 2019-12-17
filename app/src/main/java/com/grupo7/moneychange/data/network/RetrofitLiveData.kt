package com.grupo7.moneychange.data.network

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitLiveData<T>(private val call: Call<T>) : LiveData<T>(), Callback<T> {

    override fun onActive() {
        if (!call.isCanceled && !call.isExecuted) call.enqueue(this)
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        onFailResponse()
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        when {
            response?.body() != null -> value = response.body()
            response?.errorBody() != null -> onFailResponse()
            else -> onFailResponse()
        }
    }

    private fun onFailResponse() {
        value = null
    }
}

enum class ResultManager{SUCCESS, FEALURE, ERROR}

interface resultado{

    fun success()
    fun fealure()
    fun error()
}
