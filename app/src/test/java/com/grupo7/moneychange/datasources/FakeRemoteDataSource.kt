package com.grupo7.moneychange.datasources

import com.grupo7.data.ResultData
import com.grupo7.data.source.RemoteCurrencyDataSource
import com.grupo7.moneychange.fakeListCurrency

class FakeRemoteDataSource : RemoteCurrencyDataSource {

    var remoteResponse = fakeListCurrency

    override suspend fun getAllExchangeRateData() = ResultData.Success(remoteResponse)
}

