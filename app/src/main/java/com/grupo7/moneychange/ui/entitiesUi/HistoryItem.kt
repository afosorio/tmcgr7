package com.grupo7.moneychange.ui.entitiesUi

import java.io.Serializable
import java.util.*

data class HistoryItem(
    val id: Int = 0,
    val date: Date,
    val currencyFrom: String,
    val currencyTo: String,
    val valueFrom: Double,
    val valueTo: Double
) : Serializable