package com.grupo7.moneychange.data.mappers

import com.grupo7.moneychange.data.local.entity.History as LocalHistory
import com.grupo7.domain.History as DomainHistory

fun LocalHistory.toDomainHistory(): DomainHistory =
    DomainHistory(
        id,
        date,
        currencyFrom,
        currencyTo,
        valueFrom,
        valueTo
    )

fun DomainHistory.toLocalHistory(): LocalHistory =
    LocalHistory(
        id,
        date,
        currencyFrom,
        currencyTo,
        valueFrom,
        valueTo
    )