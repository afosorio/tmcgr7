package com.grupo7.data.repository

import com.grupo7.data.source.LocationDataSource
import com.grupo7.data.source.PermissionChecker
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountryRepositoryTest {
    @Mock
    lateinit var locationDataSource: LocationDataSource

    @Mock
    lateinit var permissionChecker: PermissionChecker

    private lateinit var countryRepository: CountryRepository


    @Before
    fun setUp() {
        countryRepository = CountryRepository(locationDataSource, permissionChecker)
    }

    @Test
    fun `returns default when coarse permission not granted`() {
        runBlocking {

            whenever(permissionChecker.check(PermissionChecker.Permission.COARSE_LOCATION)).thenReturn(false)

            val country = countryRepository.getCountryLocation()

            Assert.assertEquals(CountryRepository.DEFAULT_COUNTRY_NAME, country)
        }
    }


    @Test
    fun `returns default country when permission is granted`() {
        runBlocking {
            whenever(permissionChecker.check(PermissionChecker.Permission.COARSE_LOCATION)).thenReturn(true)
            whenever(locationDataSource.findLastLocation()).thenReturn("CO")
            val currentCountry = countryRepository.getCountryLocation()
            assertEquals("CO", currentCountry)
        }

    }
}