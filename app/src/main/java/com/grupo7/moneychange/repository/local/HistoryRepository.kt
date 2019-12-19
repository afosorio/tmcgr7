package com.grupo7.moneychange.repository.local


import android.content.Context
import androidx.lifecycle.LiveData
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.data.local.dao.HistoryDao
import com.grupo7.moneychange.data.local.entity.History
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * afosorio 23.11.2019
 */
class HistoryRepository(context: Context) : HistoryDataSource {

    private lateinit var historyDao: HistoryDao
    private var allHistory: LiveData<List<History>>

    init {
        MoneyChangeDb.getInstance(context)?.historyDao()?.let {
            historyDao = it
        }
        allHistory = historyDao.getAll()
    }

    override suspend fun insert(history: History) = withContext(Dispatchers.IO) {
        historyDao.insert(history)
    }

    override suspend fun findById(id: Int): History = withContext(Dispatchers.IO) {
        historyDao.findById(id)
    }

    override fun getAll(): LiveData<List<History>> = allHistory

//    override fun getAll(): LiveData<List<History>> = {
//        return historyDao.getAll()
//    }
}


