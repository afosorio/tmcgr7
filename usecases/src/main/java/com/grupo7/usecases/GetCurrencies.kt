package com.grupo7.usecases

import com.grupo7.data.repository.CurrencyRepository
import com.grupo7.domain.Currency

class GetCurrencies(private val currencyRepository: CurrencyRepository) {
    suspend fun invoke(): List<Currency> = currencyRepository.getCurrencies()
}