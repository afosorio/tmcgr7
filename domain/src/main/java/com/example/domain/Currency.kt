package com.example.domain

data class Currency(
    var id: Int = 0,
    var description: String,
    var icon: String,
    var value: Double
)