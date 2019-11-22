package com.grupo7.moneychange.data.endpoints

import com.grupo7.moneychange.models.response.LiveResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface liveApi {
    @POST("live{access_key}")
    fun changeStatus(@Path("access_key") accessKey: String): Call<LiveResponse>

}