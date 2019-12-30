package com.grupo7.moneychange.data.local

import androidx.room.Database
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

    abstract fun historyDao(): HistoryDao
    abstract fun currencyDao(): CurrencyDao
}