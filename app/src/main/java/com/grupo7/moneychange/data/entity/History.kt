package com.grupo7.moneychange.data.entity

import java.util.*

class History (
    val date:Date,
    val currencyFrom:Currency,
    val currencyTo:Currency,
    val valueFrom : Double,
    val valueTo: Double,
    val valueBase : Double
)