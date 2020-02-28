package com.grupo7.moneychange.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    foreignKeys = [
        ForeignKey(entity = Currency::class, parentColumns = arrayOf("id"), childColumns = arrayOf("currencyFrom"), onDelete = ForeignKey.NO_ACTION),
        ForeignKey(entity = Currency::class, parentColumns = arrayOf("id"), childColumns = arrayOf("currencyTo"), onDelete = ForeignKey.NO_ACTION)]
)
class History(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date,
    val currencyFrom: Int,
    val currencyTo: Int,
    val valueFrom: Double,
    val valueTo: Double
) {
    override fun toString(): String {
        return valueTo.toString()
    }
}