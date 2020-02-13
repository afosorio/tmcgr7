package com.grupo7.data.repository

import com.grupo7.data.source.HistoryDataSource
import com.grupo7.domain.History

class HistoryRepository(
    private val localDataSource: HistoryDataSource
) {
    suspend fun getHistory(): List<History> = localDataSource.getHistory()
}