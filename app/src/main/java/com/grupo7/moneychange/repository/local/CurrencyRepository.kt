package com.grupo7.moneychange.repository.local

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.data.local.dao.CurrencyDao
import com.grupo7.moneychange.data.local.entity.Currency

/**
 * afosorio 23.11.2019
 */
class CurrencyRepository(context: Context) {

    private lateinit var currencyDao: CurrencyDao
    private var allCurrency: LiveData<List<Currency>>

    init {
        MoneyChangeDb.getInstance(context)?.currencyDao()?.let {
            currencyDao = it
        }
        allCurrency = currencyDao.getAll()
    }

    fun insert(currency: Currency) {
        InsertAsyncTask(currencyDao).execute(currency)
    }

    fun getAll(): LiveData<List<Currency>> = allCurrency

    fun insertCurrencyList(newList : List<Currency>){

        val oldList= getAll().value
        val mapList : MutableMap<String, Currency> = mutableMapOf()

        if (oldList != null) {
            for(currency in oldList){
                mapList[currency.description] = currency
            }
        }

        for (newCurrency in newList){

            val oldCurrency : Currency? = mapList.get(newCurrency.description)
            if(!oldCurrency?.description.equals(newCurrency.description)){
                insert(newCurrency)
            }
        }
    }

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

