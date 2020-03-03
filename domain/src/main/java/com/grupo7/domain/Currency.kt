package com.grupo7.domain

data class Currency(
    var id: Int = 0,
    var description: String = "",
    var icon: String,
    var value: Double = 0.0
) {
    override fun toString(): String {
        return this.description.substring(3, 6)
    }
}