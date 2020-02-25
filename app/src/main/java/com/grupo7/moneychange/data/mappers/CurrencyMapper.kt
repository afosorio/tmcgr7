package com.grupo7.moneychange.data.mappers

import com.grupo7.moneychange.data.local.entity.Currency as DataBaseCurrency
import com.grupo7.domain.Currency as DomainCurrency

fun Map<String, Double>?.toDomainCurrency(): List<DomainCurrency> =
    this?.map {
        DomainCurrency(
            id = 0,
            description = it.key,
            value = it.value,
            icon = ""
        )
    } ?: emptyList()

fun List<DomainCurrency>.toDataBaseCurrency(): List<DataBaseCurrency> =
    this.map { it.toDataBaseCurrency() }

fun DomainCurrency.toDataBaseCurrency(): DataBaseCurrency =
    DataBaseCurrency(
        id = id,
        description = description,
        value = value,
        icon = icon
    )

fun List<DataBaseCurrency>.toDomainCurrency(): List<DomainCurrency> =
    this.map { it.toDomainCurrency() }

fun DataBaseCurrency.toDomainCurrency(): DomainCurrency =
    DomainCurrency(
        id,
        description,
        icon,
        value
    )