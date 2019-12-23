package com.grupo7.moneychange.data.endpoints

import com.grupo7.moneychange.models.response.LiveResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LiveApi {
    // "live" - get the most recent exchange rate data
    @GET("live")
    fun getLive(@Query("access_key") accessKey: String): Call<LiveResponse>

}