package com.grupo7.moneychange.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.grupo7.moneychange.Repository.CurrencyRepository
import com.grupo7.moneychange.data.entity.Currency

/**
 * afosorio 23.11.2019
 */

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CurrencyRepository(application)

    val currencys  = repository.getAll()

    fun save(currency: Currency) {
        repository.insert(currency)
    }
}