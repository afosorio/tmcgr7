package com.grupo7.moneychange.repository.network

import com.grupo7.moneychange.data.network.endpoints.LiveApi
import com.grupo7.moneychange.data.network.models.LiveResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LiveRepositoryImpl(private val liveApi: LiveApi) :
    LiveRepository {

    override suspend fun getLive(): LiveResponse? =
        withContext(Dispatchers.IO) {
                liveApi.getLive(Constans.Key.ACCESS_KEY).execute().body()
            }
        }

fun <T> Response<T>.getData(): T? {
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
