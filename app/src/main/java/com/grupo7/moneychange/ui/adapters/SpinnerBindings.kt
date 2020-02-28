package com.grupo7.moneychange.ui.adapters

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.grupo7.moneychange.R
import com.grupo7.domain.Currency


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

@BindingAdapter(value = ["selectedValue", "selectedValueAttrChanged"], requireAll = false)
fun bindSpinnerData(pAppCompatSpinner: AppCompatSpinner, newSelectedValue: Currency?,
                    newTextAttrChanged: InverseBindingListener
) {
    pAppCompatSpinner.onItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            newTextAttrChanged.onChange()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    if (newSelectedValue != null) {
        val pos = (pAppCompatSpinner.adapter as ArrayAdapter<Currency?>).getPosition(newSelectedValue)
        pAppCompatSpinner.setSelection(pos, true)
    }
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun captureSelectedValue(pAppCompatSpinner: AppCompatSpinner): Currency? {
    return pAppCompatSpinner.selectedItem as Currency
}