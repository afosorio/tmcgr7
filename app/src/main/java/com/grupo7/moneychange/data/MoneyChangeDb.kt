package com.grupo7.moneychange.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo7.moneychange.data.dao.CurrencyDao
import com.grupo7.moneychange.data.dao.HistoryDao
import com.grupo7.moneychange.data.entity.Currency
import com.grupo7.moneychange.data.entity.History

/**
 * afosorio 23.11.2019
 * This class contain database instance
 */
@Database(entities = [Currency::class, History:: class], version = 1)
abstract class MoneyChangeDb : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
    abstract fun currencyDao() : CurrencyDao

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
                ).build()
            }
            return INSTANCE
        }
    }
}

