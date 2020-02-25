package com.grupo7.domain

import java.util.*

class History (
    val id: Int = 0,
    val date: Date,
    val currencyFrom:Int,
    val currencyTo:Int,
    val valueFrom : Double,
    val valueTo: Double
){
    override fun toString(): String {
        return valueTo.toString()
    }
}