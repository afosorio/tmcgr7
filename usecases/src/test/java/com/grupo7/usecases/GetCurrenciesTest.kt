package com.grupo7.usecases

import com.delarosa.testshared.mockedCurrency
import com.grupo7.data.ResultData
import com.grupo7.data.repository.CurrencyRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCurrenciesTest {

    @Mock
    lateinit var currencyRepository: CurrencyRepository

    lateinit var getCurrencies: GetCurrencies


    @Before
    fun setUp() {
        getCurrencies = GetCurrencies(currencyRepository)
    }

    @Test
    fun `invoke calls currency repository`() {
        runBlocking {
            val currency = listOf(mockedCurrency.copy(id = 1))
            whenever(currencyRepository.getCurrencies()).thenReturn(ResultData.Success(currency))
            when (val result = getCurrencies.invoke()) {
                is ResultData.Success -> {
                    Assert.assertEquals(currency,  result.data)
                }
            }
        }
    }
}