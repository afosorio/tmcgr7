package com.grupo7.moneychange.adapters

import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import com.grupo7.moneychange.R
import com.grupo7.moneychange.data.entity.Currency

@BindingAdapter("coin_values")
fun setCoinValues(appCompatSpinner: AppCompatSpinner, currencyList: List<Currency>) {

    val adapter = ArrayAdapter(appCompatSpinner.context, R.layout.spinner_item, currencyList)
    adapter.setDropDownViewResource(R.layout.spinner_item)

    appCompatSpinner.apply {
        this.adapter = adapter
    }
}


@BindingAdapter("coin_values_from")
fun setCoinValuesFrom(appCompatSpinner: AppCompatSpinner, currencyList: List<Currency>) {

    val listOfItems = mutableListOf<String>()
    listOfItems.add("USD")

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


