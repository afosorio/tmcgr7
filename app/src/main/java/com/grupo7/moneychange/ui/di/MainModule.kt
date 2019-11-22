package com.grupo7.moneychange.ui.di

import com.grupo7.moneychange.R
import com.grupo7.moneychange.data.network.RetrofitBuild
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val retrofitModule = module {
    single { RetrofitBuild(androidContext().resources.getString(R.string.base_url)) }
}