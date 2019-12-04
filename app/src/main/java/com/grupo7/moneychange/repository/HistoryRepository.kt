package com.grupo7.moneychange.repository


import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grupo7.moneychange.data.dao.HistoryDao
import com.grupo7.moneychange.data.MoneyChangeDb
import com.grupo7.moneychange.data.dao.CurrencyDao
import com.grupo7.moneychange.data.entity.Currency
import com.grupo7.moneychange.data.entity.History
/**
 * afosorio 23.11.2019
 */
class HistoryRepository(context: Context) {

    private lateinit var historyDao: HistoryDao
    private lateinit var allHistory: LiveData<List<History>>

    init {
        MoneyChangeDb.getInstance(context)?.historyDao()?.let {
            historyDao = it
        }
        allHistory = historyDao.getAll()
    }

    fun insert(history: History) {
        if (history != null) InsertAsyncTask(historyDao).execute(history)
    }

    fun getAll(): LiveData<List<History>> = allHistory

    private class InsertAsyncTask(private val historyDao: HistoryDao?) :
        AsyncTask<History, Void, Void>() {
        override fun doInBackground(vararg historys: History?): Void? {
            for (history in historys) {
                if (history != null) historyDao?.insert(history)
            }
            return null
        }
    }
}


