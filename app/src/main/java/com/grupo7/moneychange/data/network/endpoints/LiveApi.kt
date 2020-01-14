package com.grupo7.moneychange.data.network.endpoints

import com.grupo7.moneychange.data.network.models.LiveResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LiveApi {
    // "live" - get the most recent exchange rate data
    @GET("live")
    fun getLive(@Query("access_key") accessKey: String): Response<LiveResponse>

}