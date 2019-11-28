package com.grupo7.moneychange.adapters

import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import com.grupo7.moneychange.R
import com.grupo7.moneychange.data.entity.Currency

@BindingAdapter("coin_values")
fun setCoinValues(appCompatSpinner: AppCompatSpinner, currencyList: List<Currency>) {

    val listOfItems = mutableListOf<String>()

    currencyList.let { list ->
        if (!list.isNullOrEmpty()) {
            list.forEach { currency ->
                listOfItems.add(currency.description)
            }
        }
    }

    val adapter = ArrayAdapter(appCompatSpinner.context, R.layout.spinner_item, listOfItems)
    adapter.setDropDownViewResource(R.layout.spinner_item)

    appCompatSpinner.apply {
        this.adapter = adapter
    }

}


@BindingAdapter("coin_values_two")
fun setCoinValuesTwo(appCompatSpinner: AppCompatSpinner, currencyList: List<String>) {
    val adapter = ArrayAdapter(appCompatSpinner.context, R.layout.spinner_item, currencyList)
    adapter.setDropDownViewResource(R.layout.spinner_item)
    appCompatSpinner.apply {
        this.adapter = adapter
    }
}