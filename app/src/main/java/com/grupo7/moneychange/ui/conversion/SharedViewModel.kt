package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grupo7.moneychange.data.entity.History


/**
 * Created by Juan Vivas on 2019-12-09
 */

class SharedViewModel : ViewModel() {

    private val conversionStates: MutableLiveData<ConversionStates> = MutableLiveData()
    fun getConversionStates(): LiveData<ConversionStates> = conversionStates

    private val _selected = MutableLiveData<History>()
    val selected: LiveData<History> = _selected

    fun select(item: History) {
        _selected.value = item
        conversionStates.value = ConversionStates.NavigateDetail
        conversionStates.value = ConversionStates.None
    }
}