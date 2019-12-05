package com.grupo7.moneychange.data.repositories

import com.grupo7.moneychange.data.endpoints.LiveApi
import com.grupo7.moneychange.data.network.RetrofitLiveData
import com.grupo7.moneychange.models.response.LiveResponse

class LiveRepositoryImpl(private val liveApi: LiveApi):
    LiveRepository {
    override fun getLive(): RetrofitLiveData<LiveResponse> =
        RetrofitLiveData(liveApi.getLive(Constans.Key.ACCESS_KEY))
}