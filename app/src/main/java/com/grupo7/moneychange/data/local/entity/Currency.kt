package com.grupo7.moneychange.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Currency(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var description: String,
    var icon: String,
    var value: Double
)