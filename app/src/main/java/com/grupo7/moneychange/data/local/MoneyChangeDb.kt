package com.grupo7.moneychange.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.grupo7.moneychange.data.local.dao.CurrencyDao
import com.grupo7.moneychange.data.local.dao.HistoryDao
import com.grupo7.moneychange.data.local.entity.Currency
import com.grupo7.moneychange.data.local.entity.History
import com.grupo7.moneychange.utils.Converters

/**
 * afosorio 23.11.2019
 * This class contain database instance
 */

@Database(entities = [Currency::class, History::class], version = 2)
@TypeConverters(Converters::class)
abstract class MoneyChangeDb : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
    abstract fun currencyDao(): CurrencyDao

    companion object {

        private const val DATABASE_NAME = "money_change_db"

        @Volatile
        private var INSTANCE: MoneyChangeDb? = null

        fun getInstance(context: Context): MoneyChangeDb? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MoneyChangeDb::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }
    }
}

