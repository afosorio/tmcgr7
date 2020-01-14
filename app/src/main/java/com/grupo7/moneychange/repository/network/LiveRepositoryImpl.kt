package com.grupo7.moneychange.repository.network

import com.grupo7.moneychange.data.network.endpoints.LiveApi
import com.grupo7.moneychange.data.network.models.LiveResponse
import retrofit2.Response

class LiveRepositoryImpl(private val liveApi: LiveApi) :
    LiveRepository {
    override fun getLive(): LiveResponse? = liveApi.getLive(Constans.Key.ACCESS_KEY).getData()
}

inline fun <reified T> Response<T>.getData(): T? {
    return try {
        if (isSuccessful) {
            this.body()
        } else {
            null
        }
    } catch (ex: Exception) {
        null
    }
}
