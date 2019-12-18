package com.grupo7.moneychange.data.network.models

data class LiveResponse(
    val privacy: String,
    val quotes: MutableMap<String, Double>,
    val source: String,
    val success: Boolean,
    val terms: String,
    val timestamp: Int
)