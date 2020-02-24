package com.grupo7.moneychange.data.repository.local

import androidx.lifecycle.LiveData
import com.grupo7.moneychange.data.local.entity.History

interface HistoryDataSource {
    suspend fun insert(history: History): Long
    suspend fun findById(id: Int): History
    suspend fun getAll(): LiveData<List<History>>
}