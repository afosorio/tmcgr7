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


@Database(entities = [Currency::class, History::class], version = 3)
@TypeConverters(Converters::class)
abstract class MoneyChangeDb : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            MoneyChangeDb::class.java,
            "money_change_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun historyDao(): HistoryDao
    abstract fun currencyDao(): CurrencyDao
}