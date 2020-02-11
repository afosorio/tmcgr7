package com.grupo7.moneychange.data.network

import com.example.data.source.RemoteDataSource
import retrofit2.await
import com.example.domain.LiveResponse as DomainLiveResponse
import com.grupo7.moneychange.data.network.models.LiveResponse as RemoteLiveResponse

class MoneyExchangeDbDataSource(private val theMoneyDb: RetrofitBuild) : RemoteDataSource {
    override suspend fun getLive(apiKey: String): DomainLiveResponse =
        theMoneyDb.service.getLive(apiKey).await().toDomainLiveResponse()
}

private fun RemoteLiveResponse.toDomainLiveResponse(): DomainLiveResponse =
    DomainLiveResponse(
        privacy,
        quotes,
        source,
        success,
        terms,
        timestamp
    )
