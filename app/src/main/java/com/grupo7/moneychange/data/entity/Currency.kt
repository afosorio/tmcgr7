package com.grupo7.moneychange.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * afosorio 21.11.2019
 */
@Entity(tableName = Currency.TABLE_NAME)
data class Currency(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "description") @NotNull
    var description:String,

    @ColumnInfo(name = "icon") @NotNull
    var icon:String,

    @ColumnInfo(name = "value") @NotNull
    var value:Double

) {

    companion object {
        const val TABLE_NAME = "currency"
    }
}

