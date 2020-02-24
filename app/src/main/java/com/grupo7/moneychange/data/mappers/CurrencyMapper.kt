package com.grupo7.moneychange.data.mappers

import com.grupo7.moneychange.ui.model.Currency as ModelCurrency
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
    this.map {
        DataBaseCurrency(
            id = it.id,
            description = it.description,
            value = it.value,
            icon = it.icon
        )
    }

fun List<DataBaseCurrency>.toDomainCurrency(): List<DomainCurrency> =
    this.map {
        DomainCurrency(
            it.id,
            it.description,
            it.icon,
            it.value
        )
    }

fun List<DomainCurrency>.toModelCurrency(): List<ModelCurrency> =
    this.map {
        ModelCurrency(
            it.id,
            it.description,
            it.icon,
            it.value
        )
    }
