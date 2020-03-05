package com.grupo7.moneychange.datasources

import com.grupo7.data.source.LocalHistoryDataSource
import com.grupo7.domain.History

class FakeLocalDataSourceHistory : LocalHistoryDataSource {
    var history: List<History> = emptyList()

    override suspend fun isEmpty() = history.isEmpty()

    override suspend fun saveHistory(history: History): Long {
        this.history.toMutableList().add(history)
        return 1L
    }

    override suspend fun getHistory(): List<History> {
        return this.history.toList()
    }

    override suspend fun findById(id: Int): History = history.first { it.id == id }

    override suspend fun remove() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}