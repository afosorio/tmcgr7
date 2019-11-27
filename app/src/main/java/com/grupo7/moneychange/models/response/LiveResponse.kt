package com.grupo7.moneychange.models.response

data class LiveResponse(
    val privacy: String,
    val quotes: MutableMap<String, Double>,
    val source: String,
    val success: Boolean,
    val terms: String,
    val timestamp: Int
)