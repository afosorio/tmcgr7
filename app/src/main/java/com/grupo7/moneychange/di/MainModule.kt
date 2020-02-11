package com.grupo7.moneychange.di

import androidx.room.Room
import com.example.data.repository.CurrencyRepository
import com.example.data.repository.HistoryRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.usecases.GetCurrencies
import com.example.usecases.GetHistories
import com.grupo7.moneychange.R
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.data.local.RoomDataSource
import com.grupo7.moneychange.data.network.MoneyExchangeDbDataSource
import com.grupo7.moneychange.data.network.RetrofitBuild
import com.grupo7.moneychange.data.network.endpoints.LiveApi
import com.grupo7.moneychange.repository.CountryRepository
import com.grupo7.moneychange.repository.CountryRepositoryImpl
import com.grupo7.moneychange.repository.network.LiveRepository
import com.grupo7.moneychange.repository.network.LiveRepositoryImpl
import com.grupo7.moneychange.ui.conversion.ConversionFragment
import com.grupo7.moneychange.ui.conversion.ConversionViewModel
import com.grupo7.moneychange.ui.detail.DetailConversionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val conversionModule = module {
//    viewModel { ConversionViewModel(get(), get(), get(), get()) }
    viewModel { DetailConversionViewModel(get()) }

    factory<CountryRepository> { CountryRepositoryImpl(context = androidContext()) }
    factory<LiveRepository> { LiveRepositoryImpl(liveApi = get()) }
//    factory { CurrencyRepository(db = get()) }
//    factory { HistoryRepository(db = get()) }

    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { MoneyExchangeDbDataSource(get()) }

}
val retrofitModule = module {
    single { RetrofitBuild(androidContext().resources.getString(R.string.base_url)) }
    single { get<RetrofitBuild>().retrofit.create(LiveApi::class.java) }
}

val dbRoom = module {
    single { Room.databaseBuilder(androidContext(), MoneyChangeDb::class.java, "money_change_db").build() }
}

val scopesModule = module {
    scope(named<ConversionFragment>()){
        viewModel { ConversionViewModel(get(), get(), get(), get(), get()) }
        scoped { GetCurrencies(get()) }
        scoped { GetHistories(get()) }
    }
}

val dataModule = module {
    factory { CurrencyRepository(get(), get()) }
    factory { HistoryRepository(get()) }
}