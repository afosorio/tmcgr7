package com.grupo7.moneychange.repository

import androidx.lifecycle.LiveData
import com.grupo7.moneychange.data.entity.History

interface HistoryDataSource {
    suspend fun insert(history: History)
    suspend fun findById(id: Int): History
    /*suspend*/ fun getAll(): LiveData<List<History>>
}