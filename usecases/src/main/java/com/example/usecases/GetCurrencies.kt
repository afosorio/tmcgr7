package com.example.usecases

import com.example.data.repository.CurrencyRepository
import com.example.domain.Currency

class GetCurrencies(private val currencyRepository: CurrencyRepository) {
    suspend fun invoke(): List<Currency> = currencyRepository.getCurrencies()
}