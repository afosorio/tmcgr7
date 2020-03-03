package com.grupo7.moneychange.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo7.data.repository.HistoryRepository
import com.grupo7.domain.History
import com.grupo7.moneychange.ui.entitiesUi.HistoryItem
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class DetailConversionViewModel(
    private val historyRepository: HistoryRepository
) : ViewModel() {


    private val _historyData = MutableLiveData<HistoryItem>()
    val historyData: LiveData<HistoryItem> get() = _historyData


    val dateTextView: MutableLiveData<String> = MutableLiveData()

    fun fetchHistoryById(id: Int) {
        viewModelScope.launch {
            //TODO evaluar si este viewmodel debe llamar un  repositorio??? o se le puede pasar la informacion de la moneda por argumentos? o si se debe llamar un caso de uso.
            // TODO las siguientes lineas de codigo hay que refactorizarlas a como debe ser
            val data: History = historyRepository.findById(id)
            val history = HistoryItem(data.id, data.date, data.currencyFrom.toString(), data.currencyTo.toString(), data.valueFrom, data.valueTo)
            _historyData.value = history
            setDateFormat()
        }
    }

    private fun setDateFormat() {
        if (historyData.value != null) {
            val sdf = SimpleDateFormat("dd-MMMM-yyyy")
            dateTextView.value = sdf.format(historyData.value?.date)
        }
    }
}
