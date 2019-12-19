package com.grupo7.moneychange.repository.network

import com.grupo7.moneychange.data.network.endpoints.LiveApi
import com.grupo7.moneychange.data.network.RetrofitLiveData
import com.grupo7.moneychange.data.network.models.LiveResponse
import com.grupo7.moneychange.repository.network.Constans
import com.grupo7.moneychange.repository.network.LiveRepository

class LiveRepositoryImpl(private val liveApi: LiveApi):
    LiveRepository {
    override fun getLive(): RetrofitLiveData<LiveResponse> =
        RetrofitLiveData(liveApi.getLive(Constans.Key.ACCESS_KEY))
}