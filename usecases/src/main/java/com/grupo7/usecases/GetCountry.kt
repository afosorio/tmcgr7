package com.grupo7.usecases

import com.grupo7.data.repository.CountryRepository

class GetCountry(private val countryRepository: CountryRepository) {
    suspend fun invoke(): String = countryRepository.getCountryLocation()
}