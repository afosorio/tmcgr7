package com.grupo7.moneychange.app

import android.app.Application
import androidx.room.Room
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.di.initDI

class App : Application() {

    private val DATABASE_NAME = "money_change_db"

    lateinit var db: MoneyChangeDb
        private set

    override fun onCreate() {
        super.onCreate()
        // Start DataBase
        db = Room.databaseBuilder(this@App, MoneyChangeDb::class.java, DATABASE_NAME).build()

        // Start Koin
        initDI()
    }
}