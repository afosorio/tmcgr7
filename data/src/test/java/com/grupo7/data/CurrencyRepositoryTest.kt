package com.grupo7.data.repository

import com.delarosa.testshared.mockedCurrency
import com.grupo7.data.ResultData
import com.grupo7.data.source.LocalCurrencyDataSource
import com.grupo7.data.source.RemoteCurrencyDataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CurrencyRepositoryTest {
    @Mock
    lateinit var localCurrencyDataSource: LocalCurrencyDataSource
    @Mock
    lateinit var remoteCurrencyDataSource: RemoteCurrencyDataSource
    lateinit var currencyRepository: CurrencyRepository

    @Before
    fun setUp() {
        currencyRepository = CurrencyRepository(localCurrencyDataSource, remoteCurrencyDataSource)
    }

    @Test
    fun `localCurrencyEmpty calls remoteCurrencyDataSource `() {
        runBlocking {
            val remoteCurrency = listOf(mockedCurrency.copy(2))
            val localCurrency = listOf(mockedCurrency.copy(1))
            whenever(localCurrencyDataSource.isEmpty()).thenReturn(true)
            whenever(remoteCurrencyDataSource.getAllExchangeRateData()).thenReturn(ResultData.Success(remoteCurrency))
            whenever(localCurrencyDataSource.getCurrencies()).thenReturn(localCurrency)

            when (val result = currencyRepository.getCurrencies()) {
                is ResultData.Success -> assertEquals(localCurrency, result.data)
            }
        }
    }

    @Test
    fun `localCurrency not Empty returns list `() {
        runBlocking {
            val localCurrency = listOf(mockedCurrency.copy(1))
            whenever(localCurrencyDataSource.isEmpty()).thenReturn(false)
            whenever(localCurrencyDataSource.getCurrencies()).thenReturn(localCurrency)
            when (val result = currencyRepository.getCurrencies()) {
                is ResultData.Success -> assertEquals(localCurrency, result.data)
            }
        }
    }

    @Test
    fun `find currency By id `() {
        runBlocking {
            val currency = mockedCurrency.copy(4)
            whenever(localCurrencyDataSource.findById(4)).thenReturn(currency)
            val result = localCurrencyDataSource.findById(4)
            assertEquals(currency, result)
        }
    }

    @Test
    fun `updating currency `() {
        runBlocking {
            val currency = mockedCurrency.copy(1)
            currencyRepository.update(currency)
            verify(localCurrencyDataSource).update(currency)
        }
    }


}