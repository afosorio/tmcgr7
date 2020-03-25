package com.grupo7.moneychange.ui.conversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.delarosa.testshared.mockedCurrency
import com.delarosa.testshared.mockedHistory
import com.grupo7.data.ResultData
import com.grupo7.domain.Currency
import com.grupo7.moneychange.data.mappers.toHistoryItem
import com.grupo7.moneychange.ui.entitiesUi.HistoryItem
import com.grupo7.usecases.GetCountry
import com.grupo7.usecases.GetCurrencies
import com.grupo7.usecases.GetHistories
import com.grupo7.usecases.SaveHistory
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ConversionViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getCurrencies: GetCurrencies

    @Mock
    lateinit var getHistories: GetHistories

    @Mock
    lateinit var saveHistory: SaveHistory

    @Mock
    lateinit var getCountry: GetCountry

    @Mock
    lateinit var observerCurrencyList: Observer<List<Currency>>

    @Mock
    lateinit var observerCountry: Observer<String>

    @Mock
    lateinit var observerEditTextConversionTo: Observer<String>

    @Mock
    lateinit var observerTextViewCurrencyTo: Observer<Currency>

    @Mock
    lateinit var observerTextViewCurrencyFrom: Observer<Currency>

    @Mock
    lateinit var observerTextViewConversionFrom: Observer<String>

    @Mock
    lateinit var observerHistoryList: Observer<List<HistoryItem>>

    private lateinit var vm: ConversionViewModel

    /*I don't need the setUp at the begging*/
    private fun setUp() {
        vm = ConversionViewModel(getCurrencies, getHistories, saveHistory,getCountry, Dispatchers.Unconfined)
    }

    @Test
    fun `observing LiveData launches location permission request`() {
        runBlocking {
            setUp()
            vm.updateLocation()
            vm.country.observeForever(observerCountry)
            verify(observerCountry).onChanged(getCountry.invoke())
        }
    }

    @Test
    fun `observing LiveData currency List`() {
        runBlocking {
            val currencyList = listOf(mockedCurrency.copy(1))
            whenever(getCurrencies.invoke()).thenReturn(ResultData.Success(currencyList))

            setUp()
            vm.currencyList.observeForever(observerCurrencyList)

            verify(observerCurrencyList).onChanged(currencyList)
        }
    }

    @Test
    fun `observing LiveData history List`() {
        runBlocking {
            val currencyList = listOf(mockedCurrency.copy(1))
            val historyList = listOf(mockedHistory.copy(currencyTo = 1, id = 1))
            val historyItemList = historyList.map {
                it.toHistoryItem(currencyList)
            }
            whenever(getCurrencies.invoke()).thenReturn(ResultData.Success(currencyList))
            whenever(getHistories.invoke()).thenReturn(historyList)

            setUp()
            vm.currencyList.observeForever(observerCurrencyList)
            vm.historyList.observeForever(observerHistoryList)

            verify(observerHistoryList).onChanged(historyItemList)
        }
    }

    @Test
    fun `upload local data in textViewCurrencyFrom `() {
        runBlocking {
            val currencyList = listOf(mockedCurrency.copy(1))
            val historyItem = mockedHistory.toHistoryItem(currencyList)

            whenever(getCurrencies.invoke()).thenReturn(ResultData.Success(currencyList))

            setUp()
            vm.textViewCurrencyFrom.observeForever(observerTextViewCurrencyFrom)
            vm.clickDataUp(historyItem)

            verify(observerTextViewCurrencyFrom).onChanged(currencyList.first())
        }
    }

    @Test
    fun `upload local data in textViewCurrencyTo `() {
        runBlocking {
            val currencyList = listOf(mockedCurrency.copy(1))
            val history = mockedHistory.toHistoryItem(currencyList)
            val historyItem= history.copy(currencyTo = "USDCOP")

            whenever(getCurrencies.invoke()).thenReturn(ResultData.Success(currencyList))
            setUp()
            vm.textViewCurrencyTo.observeForever(observerTextViewCurrencyTo)
            vm.clickDataUp(historyItem)

            verify(observerTextViewCurrencyTo).onChanged(currencyList.first())
        }
    }

    @Test
    fun `upload local data in textViewConversionFrom `() {
        runBlocking {
            val currencyList = listOf(mockedCurrency.copy(1))
            val historyItem=  mockedHistory.toHistoryItem(currencyList)

            setUp()
            vm.textViewConversionFrom.observeForever(observerTextViewConversionFrom)
            vm.clickDataUp(historyItem)

            verify(observerTextViewConversionFrom).onChanged("1")
        }
    }

    @Test
    fun `upload local data in textViewConversionTo `() {
        runBlocking {
            val currencyList = listOf(mockedCurrency.copy(1))
            val history = mockedHistory.toHistoryItem(currencyList)
            val historyItem= history.copy(valueTo = 4.5)

            setUp()
            vm.editTextConversionTo.observeForever(observerEditTextConversionTo)
            vm.clickDataUp(historyItem)

            verify(observerEditTextConversionTo).onChanged("4.5")
        }
    }


    @Test
    fun `doing the conversion`() {
        val from = "4444"
        val currency = mockedCurrency.copy(id = 1)
        setUp()
        vm.editTextConversionTo.observeForever(observerEditTextConversionTo)
        vm.onClickChange(from, currency)
    }

    @Test
    fun `saving history`() {
        runBlocking {
            val history = mockedHistory.copy(valueTo = 15554.0, id = 1)
            val from = "4444"
            val currency = mockedCurrency.copy(id = 1)

            setUp()
            vm.onClickChange(from, currency)
            whenever(saveHistory.invoke(history)).thenReturn(1L)
            val result = saveHistory.invoke(history)

            Assert.assertEquals(history.id, result.toInt())
        }
    }

    @Test
    fun `doing the conversion with null data`() {
        runBlocking {
            val history = mockedHistory.copy(1)
            val from = null
            val currency = null
            setUp()
            vm.onClickChange(from, currency)
            verify(saveHistory, times(0)).invoke(history)
        }
    }
}