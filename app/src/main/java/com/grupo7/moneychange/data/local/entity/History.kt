package com.grupo7.moneychange.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(tableName = History.TABLE_NAME,
        foreignKeys = [
            ForeignKey(entity = Currency::class, parentColumns = arrayOf("id"), childColumns = arrayOf("currencyFrom"), onDelete = ForeignKey.NO_ACTION),
            ForeignKey(entity = Currency::class, parentColumns = arrayOf("id"), childColumns = arrayOf("currencyTo"), onDelete = ForeignKey.NO_ACTION)]
    )
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