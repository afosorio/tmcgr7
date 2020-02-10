package com.example.data.source

import com.example.domain.Currency

interface CurrencyDataSource {
    fun getCurrencies(): List<Currency>
}
