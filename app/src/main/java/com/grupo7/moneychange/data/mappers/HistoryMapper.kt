package com.grupo7.moneychange.data.mappers

import com.grupo7.domain.Currency
import com.grupo7.moneychange.ui.entitiesUi.HistoryItem
import com.grupo7.domain.History as DomainHistory
import com.grupo7.moneychange.data.local.entity.History as LocalHistory

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

fun DomainHistory.toHistoryItem(list: List<Currency>): HistoryItem =
    HistoryItem(
        id,
        date,
        getDescription(list, currencyFrom),
        getDescription(list, currencyTo),
        valueFrom,
        valueTo
    )

fun getDescription(list: List<Currency>, value: Int): String {
    val founded = list.find { it.id == value }
    founded?.let {
        return it.description
    }
    return ""
}
