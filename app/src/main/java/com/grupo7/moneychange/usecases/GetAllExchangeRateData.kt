package com.grupo7.moneychange.usecases

import com.grupo7.moneychange.data.repository.network.ConversionRepository

class GetAllExchangeRateData(private val conversionRepository: ConversionRepository) {
    suspend fun invoke() = conversionRepository.getAllExchangeRateData()
}