package com.grupo7.moneychange.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(tableName = History.TABLE_NAME,
        foreignKeys = [
            ForeignKey(entity = Currency::class, parentColumns = arrayOf("id"), childColumns = arrayOf("currencyfrom"), onDelete = ForeignKey.NO_ACTION),
            ForeignKey(entity = Currency::class, parentColumns = arrayOf("id"), childColumns = arrayOf("currencyto"), onDelete = ForeignKey.NO_ACTION)]
    )
class History (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "date")
    val date: Date,

    @ColumnInfo(name = "currencyfrom")
    val currencyFrom:Int,

    @ColumnInfo(name = "currencyto")
    val currencyTo:Int,

    @ColumnInfo(name = "valuefrom") @NotNull
    val valueFrom : Double,

    @ColumnInfo(name = "valueto") @NotNull
    val valueTo: Double

) {

    companion object{
        const val  TABLE_NAME = "history"
    }

    override fun toString(): String {
        return valueTo.toString()
    }
}