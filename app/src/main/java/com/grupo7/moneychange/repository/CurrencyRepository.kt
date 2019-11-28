package com.grupo7.moneychange.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.grupo7.moneychange.data.MoneyChangeDb
import com.grupo7.moneychange.data.dao.CurrencyDao
import com.grupo7.moneychange.data.entity.Currency

/**
 * afosorio 23.11.2019
 */
class CurrencyRepository(context: Context) {

    private lateinit var currencyDao: CurrencyDao
    private lateinit var allCurrency: LiveData<List<Currency>>

    init {
        MoneyChangeDb.getInstance(context)?.currencyDao()?.let {
            currencyDao = it
        }
        allCurrency = currencyDao.getAll()
    }

    fun insert(currency: Currency) {
        if (currency != null) InsertAsyncTask(currencyDao).execute(currency)
    }

    fun getAll(): LiveData<List<Currency>> = allCurrency

    private class InsertAsyncTask(private val currencyDao: CurrencyDao?) :
        AsyncTask<Currency, Void, Void>() {
        override fun doInBackground(vararg currencys: Currency?): Void? {
            for (currency in currencys) {
                if (currency != null) currencyDao?.insert(currency)
            }
            return null
        }
    }
}

