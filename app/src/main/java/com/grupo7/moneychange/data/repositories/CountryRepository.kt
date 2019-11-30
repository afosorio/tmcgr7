package com.grupo7.moneychange.data.repositories

interface CountryRepository {
   suspend fun getCountryLocation(): String
}

