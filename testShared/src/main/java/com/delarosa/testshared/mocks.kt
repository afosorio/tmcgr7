package com.delarosa.testshared

import com.grupo7.domain.Currency
import com.grupo7.domain.History
import java.util.*

val mockedCurrency = Currency(
    id = 0,
    description = "USDCOP",
    icon = "dd",
    value = 3.500
)

val mockedHistory = History(
    id = 0,
    date = Date(),
    currencyFrom = 1,
    currencyTo = 12,
    valueFrom = 1.0,
    valueTo = 3.500
)

