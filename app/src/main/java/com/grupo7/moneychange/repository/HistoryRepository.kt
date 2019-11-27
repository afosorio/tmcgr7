package com.grupo7.moneychange.repository


import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grupo7.moneychange.data.dao.HistoryDao
import com.grupo7.moneychange.data.MoneyChangeDb
import com.grupo7.moneychange.data.entity.History
/**
 * afosorio 23.11.2019
 */
class HistoryRepository(context: Context) {

    private val historyDao: HistoryDao? = MoneyChangeDb.getInstance(context)?.historyDao()

    fun insert(history: History) {
        if (history != null) InsertAsyncTask(historyDao).execute(history)
    }

    fun getAll(): LiveData<List<History>> {
        return historyDao?.getAll() ?: MutableLiveData<List<History>>()
    }

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


