package com.grupo7.moneychange.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * afosorio 21.11.2019
 */
@Entity(tableName = Currency.TABLE_NAME)
data class Currency(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var description: String,
    var icon: String,
    var value: Double
) {

    companion object {
        const val TABLE_NAME = "currency"
    }
}

