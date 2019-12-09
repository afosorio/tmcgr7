package com.grupo7.moneychange.ui.conversion

import com.grupo7.moneychange.data.entity.History


/**
 * Created by Juan Vivas on 2019-12-09
 */
sealed class ConversionStates {
    object NavigateDetail : ConversionStates()
    object None: ConversionStates()
    class ItemSelected(val item: History): ConversionStates()
}