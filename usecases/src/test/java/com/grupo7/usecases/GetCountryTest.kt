package com.grupo7.usecases

import com.grupo7.data.repository.CountryRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCountryTest {
    @Mock
    lateinit var countryRepository: CountryRepository

    lateinit var getCountry: GetCountry

    @Before
    fun setup() {
        getCountry = GetCountry(countryRepository)
    }

    @Test
    fun `validate get country`() {
        runBlocking {
            val country = "Colombia"
            whenever(countryRepository.getCountryLocation()).thenReturn("Colombia")
            val result = getCountry.invoke()
            Assert.assertEquals(country, result)
        }
    }

}
