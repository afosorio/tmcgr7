package com.grupo7.moneychange.di

import android.app.Application
import androidx.room.Room

import com.grupo7.usecases.GetCurrencies
import com.grupo7.usecases.GetHistories
import com.grupo7.moneychange.R
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.data.network.RetrofitBuild
import com.grupo7.moneychange.data.repository.network.ConversionRepository
import com.grupo7.moneychange.data.repository.network.ConversionRepositoryImpl
import com.grupo7.moneychange.data.source.LiveDataSource
import com.grupo7.moneychange.data.source.LiveDataSourceImpl
import com.grupo7.moneychange.ui.conversion.ConversionFragment
import com.grupo7.moneychange.data.network.endpoints.LiveApi
import com.grupo7.moneychange.data.repository.CountryRepository
import com.grupo7.moneychange.data.repository.CountryRepositoryImpl
import com.grupo7.moneychange.data.repository.local.CurrencyRepository
import com.grupo7.moneychange.data.repository.local.HistoryRepository
import com.grupo7.moneychange.ui.conversion.ConversionViewModel
import com.grupo7.moneychange.ui.detail.DetailConversionViewModel
import com.grupo7.moneychange.usecases.GetAllExchangeRateData
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(presentationModule, dataModule, appModule))
    }
}

val presentationModule = module {
    scope(named<ConversionFragment>()) {
        viewModel { ConversionViewModel(get(), get(), get()) }
        scoped { GetAllExchangeRateData(get()) }
    }
    viewModel { DetailConversionViewModel(get()) }
}

val dataModule = module {
    factory<CountryRepository> { CountryRepositoryImpl(context = get()) }
    factory<ConversionRepository> { ConversionRepositoryImpl(liveDataSource = get(), currentRepository = get()) }
    factory { CurrencyRepository(db = get()) }
    factory { HistoryRepository(db = get()) }
}

val appModule = module {
    single { RetrofitBuild(androidContext().resources.getString(R.string.base_url)) }
    single { get<RetrofitBuild>().retrofit.create(LiveApi::class.java) }
    single { Room.databaseBuilder(androidContext(), MoneyChangeDb::class.java, "money_change_db").build() }
    factory<LiveDataSource> { LiveDataSourceImpl(get()) }
}

/*
val scopesModule = module {
    scope(named<ConversionFragment>()){
        viewModel { ConversionViewModel(get(), get(), get(), get(), get()) }
        scoped { GetCurrencies(get()) }
        scoped { GetHistories(get()) }
    }
}
*/

/*
val dataModule = module {
    factory { CurrencyRepository(get(), get()) }
    factory { HistoryRepository(get()) }
}*/
