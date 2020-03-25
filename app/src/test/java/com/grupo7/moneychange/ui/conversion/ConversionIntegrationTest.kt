package com.grupo7.moneychange.ui.conversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.delarosa.testshared.mockedCurrency
import com.delarosa.testshared.mockedHistory
import com.grupo7.data.source.LocalCurrencyDataSource
import com.grupo7.data.source.LocalHistoryDataSource
import com.grupo7.data.source.LocationDataSource
import com.grupo7.domain.Currency
import com.grupo7.moneychange.data.mappers.toHistoryItem
import com.grupo7.moneychange.datasources.FakeLocalDataSource
import com.grupo7.moneychange.datasources.FakeLocalDataSourceHistory
import com.grupo7.moneychange.datasources.FakeLocationDataSource
import com.grupo7.moneychange.fakeListCurrency
import com.grupo7.moneychange.initMockedDi
import com.grupo7.moneychange.ui.entitiesUi.HistoryItem
import com.grupo7.usecases.GetCountry
import com.grupo7.usecases.GetCurrencies
import com.grupo7.usecases.GetHistories
import com.grupo7.usecases.SaveHistory
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ConversionIntegrationTest : AutoCloseKoinTest() {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getCurrencies: GetCurrencies

    @Mock
    lateinit var saveHistory: SaveHistory

    @Mock
    lateinit var observerCurrencyList: Observer<List<Currency>>

    @Mock
    lateinit var observerCountry: Observer<String>

    @Mock
    lateinit var observerHistoryList: Observer<List<HistoryItem>>

    private lateinit var vm: ConversionViewModel

    @Before
    fun setUp() {
        val vmModule = module {
            factory { ConversionViewModel(get(), get(), get(), get(), get()) }
            factory { GetCurrencies(get()) }
            factory { GetCountry(get()) }
            factory { GetHistories(get()) }
            factory { SaveHistory(get()) }
        }

        initMockedDi(vmModule)

    }

    @Test
    fun `currency data is loaded from server when local source is empty`() {
        vm = get()
        vm.currencyList.observeForever(observerCurrencyList)
        verify(observerCurrencyList).onChanged(fakeListCurrency)
    }

    @Test
    fun `currency data is loaded from local source when available`() {
        val fakeLocalCurrency = listOf(mockedCurrency.copy(10), mockedCurrency.copy(11))
        val localDataSource = get<LocalCurrencyDataSource>() as FakeLocalDataSource
        localDataSource.currency = fakeLocalCurrency
        vm = get()
        vm.currencyList.observeForever(observerCurrencyList)
        verify(observerCurrencyList).onChanged(fakeLocalCurrency)
    }

    @Test
    fun `history data is loaded from local source when available`() {
        val currencyList = listOf(mockedCurrency.copy(1))
        val fakeLocalHistory = listOf(mockedHistory.copy(9), mockedHistory.copy(11))
        val localDataSource = get<LocalHistoryDataSource>() as FakeLocalDataSourceHistory
        localDataSource.history = fakeLocalHistory
        vm = get()
        vm.historyList.observeForever(observerHistoryList)
        verify(observerHistoryList).onChanged(fakeLocalHistory.map { it.toHistoryItem(currencyList) })
    }

    @Test
    fun `setting new default location`() {
        val location = get<LocationDataSource>() as FakeLocationDataSource
        location.location = "COUNTRY-N"
        vm = get()
        vm.country.observeForever(observerCountry)
        vm.updateLocation()
        verify(observerCountry).onChanged("COUNTRY-N")
    }

}