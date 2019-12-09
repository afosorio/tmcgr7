package com.grupo7.moneychange.ui.di

import com.grupo7.moneychange.R
import com.grupo7.moneychange.data.endpoints.LiveApi
import com.grupo7.moneychange.data.network.RetrofitBuild
import com.grupo7.moneychange.data.repositories.CountryRepository
import com.grupo7.moneychange.data.repositories.LiveRepository
import com.grupo7.moneychange.data.repositories.LiveRepositoryImpl
import com.grupo7.moneychange.repository.CountryRepositoryImpl
import com.grupo7.moneychange.repository.CurrencyRepository
import com.grupo7.moneychange.repository.HistoryRepository
import com.grupo7.moneychange.ui.conversion.ConversionViewModel
import com.grupo7.moneychange.ui.conversion.SharedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val conversionModule = module {
    viewModel {
        ConversionViewModel(
            liveRepository = get(),
            currentRepository = get(),
            historyRepository = get(),
            countryRepository = get()
        )
    }

    viewModel {
        SharedViewModel()
    }

    factory<CountryRepository> { CountryRepositoryImpl(context = androidContext()) }
    factory<LiveRepository> { LiveRepositoryImpl(liveApi = get()) }
    factory { CurrencyRepository(androidContext()) }
    factory { HistoryRepository(androidContext()) }
}
val retrofitModule = module {
    single { RetrofitBuild(androidContext().resources.getString(R.string.base_url)) }
    single { get<RetrofitBuild>().retrofit.create(LiveApi::class.java) }
}