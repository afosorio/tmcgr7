package com.grupo7.data.source

import com.grupo7.domain.History

interface LocalHistoryDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveHistory(history: History)
    suspend fun getHistory(): List<History>
    suspend fun findById(id: Int): History
    suspend fun remove()
}