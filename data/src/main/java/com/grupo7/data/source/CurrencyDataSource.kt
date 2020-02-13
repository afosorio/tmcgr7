package com.grupo7.data.source

import com.grupo7.domain.Currency

interface CurrencyDataSource {
    fun getCurrencies(): List<Currency>
}
