package com.grupo7.moneychange.di

import android.app.Application
import com.grupo7.data.repository.CountryRepository
import com.grupo7.data.repository.CurrencyRepository
import com.grupo7.data.repository.HistoryRepository
import com.grupo7.data.source.*
import com.grupo7.moneychange.R
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.data.local.source.LocalCurrencyDataSourceImpl
import com.grupo7.moneychange.data.local.source.LocalHistoryDataSourceImpl
import com.grupo7.moneychange.data.local.source.LocationDataSourceImpl
import com.grupo7.moneychange.data.network.RetrofitBuild
import com.grupo7.moneychange.data.network.endpoints.LiveApi
import com.grupo7.moneychange.data.network.source.RemoteCurrencyDataSourceImpl
import com.grupo7.moneychange.ui.conversion.ConversionFragment
import com.grupo7.moneychange.ui.conversion.ConversionViewModel
import com.grupo7.moneychange.ui.detail.DetailConversionViewModel
import com.grupo7.moneychange.utils.AndroidPermissionChecker
import com.grupo7.usecases.GetCountry
import com.grupo7.usecases.GetCurrencies
import com.grupo7.usecases.GetHistories
import com.grupo7.usecases.SaveHistory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
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
        viewModel {
            ConversionViewModel(
                getCurrencies = get(),
                getHistories = get(),
                saveHistory = get(),
                getCountry = get(),
                uiDispatcher = get()
            )
        }
        scoped { GetCurrencies(currencyRepository = get()) }
        scoped { GetHistories(historyRepository = get()) }
        scoped { SaveHistory(historyRepository = get()) }
        scoped { GetCountry(countryRepository = get()) }

    }
    viewModel {
        DetailConversionViewModel(
            uiDispatcher = get()
        )
    }
}

val dataModule = module {
    factory { CountryRepository(locationDataSource = get(), permissionChecker = get()) }
    factory { CurrencyRepository(remoteCurrencyDataSource = get(), localCurrencyDataSource = get()) }
    factory { HistoryRepository(localHistoryDataSource = get()) }
}

val appModule = module {
    single { RetrofitBuild(androidContext().resources.getString(R.string.base_url)) }
    single { get<RetrofitBuild>().retrofit.create(LiveApi::class.java) }
    single { MoneyChangeDb.build(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    factory<RemoteCurrencyDataSource> { RemoteCurrencyDataSourceImpl(get()) }
    factory<LocalCurrencyDataSource> { LocalCurrencyDataSourceImpl(get()) }
    factory<LocalHistoryDataSource> { LocalHistoryDataSourceImpl(get()) }
    factory<LocationDataSource> { LocationDataSourceImpl(get()) }
    factory<PermissionChecker> { AndroidPermissionChecker(get()) }
}