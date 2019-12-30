package com.grupo7.moneychange.repository.local

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.grupo7.moneychange.app.App
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.data.local.dao.CurrencyDao
import com.grupo7.moneychange.data.local.entity.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyRepository(private var  db : MoneyChangeDb) : CurrencyDataSource {

    private var allCurrency: LiveData<List<Currency>> = db.currencyDao().getAll()

    override suspend fun insert(currency: Currency) = withContext(Dispatchers.IO){
        db.currencyDao().insert(currency)
    }

    override fun getAll(): LiveData<List<Currency>> =  allCurrency

    suspend fun insertCurrencyList(newList : List<Currency>) = withContext(Dispatchers.IO){

        val oldList= getAll().value
        val mapList : MutableMap<String, Currency> = mutableMapOf()

        if (oldList != null) {
            for(currency in oldList){
                mapList[currency.description] = currency
            }
        }

        for (newCurrency in newList){

            val oldCurrency : Currency? = mapList.get(newCurrency.description)
            if(oldCurrency?.description.equals(newCurrency.description)){
                if (oldCurrency != null) {
                    newCurrency.id = oldCurrency.id
                }
            }
        }
        db.currencyDao().insertCurrencies(newList)
    }
}

