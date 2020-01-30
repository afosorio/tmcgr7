package com.grupo7.moneychange.app

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.di.conversionModule
import com.grupo7.moneychange.di.dbRoom
import com.grupo7.moneychange.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    private val DATABASE_NAME = "money_change_db"

    lateinit var  db : MoneyChangeDb
        private set




    private val MIGRATION_3_4 = object : Migration(3,4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // empty migration.
        }
    }
    override fun onCreate() {
        super.onCreate()

        // Start DataBase

        db = Room.databaseBuilder(this@App, MoneyChangeDb::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()

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
        dbRoom
    )
}