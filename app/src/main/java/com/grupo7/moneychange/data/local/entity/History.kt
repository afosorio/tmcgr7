package com.grupo7.moneychange.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = History.TABLE_NAME)
class History (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date,
    val currencyFrom: String,
    val currencyTo: String,
    val valueFrom: Double,
    val valueTo: Double
) {

    companion object{
        const val  TABLE_NAME = "history"
    }

    override fun toString(): String {
        return valueTo.toString()
    }
}