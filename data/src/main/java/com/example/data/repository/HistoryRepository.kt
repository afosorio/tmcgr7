package com.example.data.repository

import com.example.data.source.HistoryDataSource
import com.example.domain.History

class HistoryRepository(
    private val localDataSource: HistoryDataSource
) {
    suspend fun getHistory(): List<History> = localDataSource.getHistory()
}