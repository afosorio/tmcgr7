package com.grupo7.moneychange.data.repository.local


import androidx.lifecycle.LiveData
import com.grupo7.moneychange.data.local.MoneyChangeDb
import com.grupo7.moneychange.data.local.entity.History
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoryRepository(private var  db : MoneyChangeDb) : HistoryDataSource {

    private var allHistory: LiveData<List<History>> = db.historyDao().getAll()

    override suspend fun insert(history: History) = withContext(Dispatchers.IO) {
        db.historyDao().insert(history)
    }

    override suspend fun findById(id: Int): History = withContext(Dispatchers.IO) {
        db.historyDao().findById(id)
    }

    override fun getAll(): LiveData<List<History>> = allHistory
}