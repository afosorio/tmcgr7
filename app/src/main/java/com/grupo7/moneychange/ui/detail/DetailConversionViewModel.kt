package com.grupo7.moneychange.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo7.moneychange.data.entity.History
import com.grupo7.moneychange.repository.HistoryRepository
import kotlinx.coroutines.launch

class DetailConversionViewModel(
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val _historyData = MutableLiveData<History>()
    val historyData: LiveData<History> get() = _historyData

    val textView: MutableLiveData<String> = MutableLiveData()


    fun fetchHistoryById(id: Int) {
        viewModelScope.launch {
            _historyData.value = historyRepository.findById(id)
        }
    }
}
