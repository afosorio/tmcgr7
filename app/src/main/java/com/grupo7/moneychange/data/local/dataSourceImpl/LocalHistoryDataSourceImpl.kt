package com.grupo7.moneychange.data.local.dataSourceImpl

import com.grupo7.data.source.LocalHistoryDataSource
import com.grupo7.domain.History
import com.grupo7.moneychange.data.local.MoneyChangeDb

class LocalHistoryDataSourceImpl(db: MoneyChangeDb) : LocalHistoryDataSource {

    private val historyDao = db.historyDao()

    override suspend fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveHistory(history: History) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getHistory(): List<History> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun findById(id: Int): History {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun remove() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}