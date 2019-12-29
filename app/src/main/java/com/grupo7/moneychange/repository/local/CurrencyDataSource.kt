package com.grupo7.moneychange.repository.local

import androidx.lifecycle.LiveData
import com.grupo7.moneychange.data.local.entity.Currency

interface CurrencyDataSource {

    suspend fun insert(currency: Currency)
    fun getAll(): LiveData<List<Currency>>
}