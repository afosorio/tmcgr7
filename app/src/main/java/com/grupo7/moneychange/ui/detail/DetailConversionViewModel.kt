package com.grupo7.moneychange.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grupo7.data.repository.HistoryRepository
import com.grupo7.moneychange.ui.common.ScopedViewModel
import com.grupo7.moneychange.ui.entitiesUi.HistoryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class DetailConversionViewModel(
    override val uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _historyData = MutableLiveData<HistoryItem>()
    val historyData: LiveData<HistoryItem> get() = _historyData

    val dateTextView: MutableLiveData<String> = MutableLiveData()

    fun fetchHistory(data: HistoryItem) {
        launch {
            val history =
                HistoryItem(
                    data.id,
                    data.date,
                    refactorString(data.currencyFrom),
                    refactorString(data.currencyTo),
                    data.valueFrom,
                    data.valueTo
                )
            _historyData.value = history
            setDateFormat()
        }
    }

    private fun refactorString(string: String): String {
        return string.substring(3, string.length)
    }

    private fun setDateFormat() {
        if (historyData.value != null) {
            val sdf = SimpleDateFormat("dd-MMMM-yyyy")
            dateTextView.value = sdf.format(historyData.value?.date)
        }
    }
}
