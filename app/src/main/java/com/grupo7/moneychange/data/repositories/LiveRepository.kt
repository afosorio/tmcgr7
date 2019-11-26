package com.grupo7.moneychange.data.repositories

import com.grupo7.moneychange.data.network.RetrofitLiveData
import com.grupo7.moneychange.models.response.LiveResponse

interface LiveRepository {
    fun getLive(): RetrofitLiveData<LiveResponse>
}