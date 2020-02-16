package com.grupo7.domain

data class LiveResponse(
    val privacy: String?,
    val quotes: Map<String, Double>?,
    val source: String?,
    val success: Boolean?,
    val terms: String?,
    val timestamp: Int?,
    val error: ErrorResponse?
)

data class ErrorResponse(
    val code: Int?,
    val info: String?
)