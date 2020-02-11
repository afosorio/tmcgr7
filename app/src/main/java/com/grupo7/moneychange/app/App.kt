package com.grupo7.moneychange.app

import android.app.Application
import androidx.room.Room
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    private val DATABASE_NAME = "money_change_db"

    lateinit var  db : MoneyChangeDb
        private set

    override fun onCreate() {
        super.onCreate()

        // Start DataBase
        db = Room.databaseBuilder(this@App, MoneyChangeDb::class.java, DATABASE_NAME).build()

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }


    }

    private val appModule = listOf(
        retrofitModule,
        conversionModule,
        dbRoom,
        scopesModule,
        dataModule
    )
}