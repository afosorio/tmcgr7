package com.grupo7.moneychange.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grupo7.moneychange.data.MoneyChangeDb
import com.grupo7.moneychange.data.dao.CurrencyDao
import com.grupo7.moneychange.data.entity.Currency

/**
 * afosorio 23.11.2019
 */
class CurrencyRepository(application: Application) {

    private val currencyDao: CurrencyDao? = MoneyChangeDb.getInstance(application)?.currencyDao()

    fun insert(currency: Currency) {
        if (currency != null) InsertAsyncTask(currencyDao).execute(currency)
    }

    fun getAll(): LiveData<List<Currency>> {
        return currencyDao?.getAll() ?: MutableLiveData<List<Currency>>()
    }

    private class InsertAsyncTask(private val  currencyDao: CurrencyDao?) :
        AsyncTask<Currency, Void, Void>() {
        override fun doInBackground(vararg currencys: Currency?): Void? {
            for (currency in currencys) {
                if (currency != null) currencyDao?.insert(currency)
            }
            return null
        }
    }
}


