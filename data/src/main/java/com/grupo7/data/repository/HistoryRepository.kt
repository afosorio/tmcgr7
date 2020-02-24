package com.grupo7.data.repository

import com.grupo7.data.source.LocalHistoryDataSource
import com.grupo7.domain.History

class HistoryRepository(
    private val localHistoryDataSource: LocalHistoryDataSource
) {
    suspend fun getHistory(): List<History> = localHistoryDataSource.getHistory()
}