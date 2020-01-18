package com.grupo7.moneychange.repository.network

import com.grupo7.moneychange.data.network.RetrofitResult
import com.grupo7.moneychange.data.network.callServices
import com.grupo7.moneychange.data.network.endpoints.LiveApi
import com.grupo7.moneychange.data.network.models.LiveResponse
import com.grupo7.moneychange.data.network.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LiveRepositoryImpl(private val liveApi: LiveApi) :
    LiveRepository {

    override suspend fun getLive(): RetrofitResult<LiveResponse> =
        withContext(Dispatchers.IO) {
            safeApiCall(
                call = { liveApi.getLive(Constans.Key.ACCESS_KEY).callServices() },
                errorMessage = "error"
            )
        }

    /*private fun <T : Any> callService(live: Response<T>): RetrofitResult<T> {
        if(live.isSuccessful){
            return if(live.body()!= null){
                RetrofitResult.Success(live.body() as T)
            } else{
                RetrofitResult.Error(IOException())
            }
        }
        return RetrofitResult.Error(IOException())
    }*/

}


/*
private suspend fun <T> Response<T>.getData(): RetrofitResult<Any>? {
        return if (isSuccessful) {
            RetrofitResult.Success(this.body())
        } else {
            RetrofitResult.Error(IOException())
        }
*/




