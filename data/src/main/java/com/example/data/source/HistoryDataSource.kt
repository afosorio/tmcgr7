package com.example.data.source

import com.example.domain.History

interface HistoryDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveHistory(history: History)
    suspend fun getHistory(): List<History>
    suspend fun findById(id: Int): History
    suspend fun remove()
}