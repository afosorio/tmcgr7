package com.grupo7.moneychange.di

import com.grupo7.moneychange.R
import com.grupo7.moneychange.data.network.endpoints.LiveApi
import com.grupo7.moneychange.data.network.RetrofitBuild
import com.grupo7.moneychange.repository.*
import com.grupo7.moneychange.repository.local.CurrencyRepository
import com.grupo7.moneychange.repository.local.HistoryRepository
import com.grupo7.moneychange.repository.network.LiveRepository
import com.grupo7.moneychange.repository.network.LiveRepositoryImpl
import com.grupo7.moneychange.ui.conversion.ConversionViewModel
import com.grupo7.moneychange.ui.detail.DetailConversionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val conversionModule = module {
    viewModel { ConversionViewModel(get(), get(), get(), get()) }
    viewModel { DetailConversionViewModel(get())}

    factory<ICountryRepository> { CountryRepositoryImpl(context = androidContext()) }
    factory<LiveRepository> { LiveRepositoryImpl(liveApi = get()) }
    factory { CurrencyRepository(androidContext()) }
    factory { HistoryRepository(androidContext()) }
}
val retrofitModule = module {
    single { RetrofitBuild(androidContext().resources.getString(R.string.base_url)) }
    single { get<RetrofitBuild>().retrofit.create(LiveApi::class.java) }
}