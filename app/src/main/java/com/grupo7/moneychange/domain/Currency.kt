package com.grupo7.moneychange.domain

data class Currency(
    val id: Int = 0,
    val description: String = "",
    val value: Double = 0.0,
    val icon: String
)