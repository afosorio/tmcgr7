package com.grupo7.moneychange.app

import android.app.Application
import com.grupo7.moneychange.di.initDI

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}