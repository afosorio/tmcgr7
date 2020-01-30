package com.grupo7.moneychange.ui.model

data class Currency(
    var id: Int,
    var description: String,
    var icon: String,
    var value: Double
) {
    override fun toString(): String {
        return this.description
    }
}

