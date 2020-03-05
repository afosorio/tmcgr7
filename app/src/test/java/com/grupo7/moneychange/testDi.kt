package com.grupo7.moneychange


import com.delarosa.testshared.mockedCurrency
import com.delarosa.testshared.mockedHistory
import com.grupo7.data.source.*
import com.grupo7.domain.Constans
import com.grupo7.moneychange.data.mappers.toHistoryItem
import com.grupo7.moneychange.datasources.*
import com.grupo7.moneychange.di.dataModule
import com.grupo7.moneychange.ui.entitiesUi.HistoryItem
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun initMockedDi(vararg modules: Module) {
    startKoin {
        modules(listOf(mockedAppModule, dataModule) + modules)
    }
}

private val mockedAppModule = module {
    single(named("access_key")) { Constans.Key.ACCESS_KEY }
    single<LocalCurrencyDataSource> { FakeLocalDataSource() }
    single<RemoteCurrencyDataSource> { FakeRemoteDataSource() }
    single<LocalHistoryDataSource> { FakeLocalDataSourceHistory() }
    single<LocationDataSource> { FakeLocationDataSource() }
    single<PermissionChecker> { FakePermissionChecker() }
    single { Dispatchers.Unconfined }
}

val fakeListCurrency = listOf(
    mockedCurrency.copy(1),
    mockedCurrency.copy(2),
    mockedCurrency.copy(3),
    mockedCurrency.copy(4)
)

val fakeListHistory = listOf(
    mockedHistory.copy(1),
    mockedHistory.copy(2),
    mockedHistory.copy(3),
    mockedHistory.copy(4)
)

fun returnHistoryItemList(): List<HistoryItem> {
    val currencyList = listOf(mockedCurrency.copy(1))
    val historyList = listOf(mockedHistory.copy(currencyTo = 1, id = 1))
    return historyList.map {
        it.toHistoryItem(currencyList)
    }
}


