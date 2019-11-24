package com.grupo7.moneychange.models.response

import com.squareup.moshi.Json

data class LiveResponse(
    val privacy: String,
    val quotes: Quotes,
    val source: String,
    val success: Boolean,
    val terms: String,
    val timestamp: Int
)

data class Quotes(
    @Json(name = "USDAED")
    val usdAed: Double,
    @Json(name = "USDAFN")
    val usdAfn: Double,
    @Json(name = "USDALL")
    val usdAll: Double,
    @Json(name = "USDAMD")
    val usdAmd: Double,
    @Json(name = "USDANG")
    val usdAng: Double
)