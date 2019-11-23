package com.grupo7.moneychange.data.dto

/**
 * afosorio 23.11.2019
 */
data class CurrencyDTO(
    val privacy: String,
    val quotes: Quotes,
    val source: String,
    val success: Boolean,
    val terms: String,
    val timestamp: Int
)