package com.grupo7.moneychange.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * afosorio  21.11.2019
 */
@Entity
open class Entity(var table: String){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    open var id: Int = 0
}