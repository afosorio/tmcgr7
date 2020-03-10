package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grupo7.data.ResultData
import com.grupo7.domain.Currency
import com.grupo7.domain.History
import com.grupo7.moneychange.data.mappers.toHistoryItem
import com.grupo7.moneychange.ui.common.ScopedViewModel
import com.grupo7.moneychange.ui.entitiesUi.HistoryItem
import com.grupo7.usecases.GetCountry
import com.grupo7.usecases.GetCurrencies
import com.grupo7.usecases.GetHistories
import com.grupo7.usecases.SaveHistory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class ConversionViewModel(
    private val getCurrencies: GetCurrencies,
    private val getHistories: GetHistories,
    private val saveHistory: SaveHistory,
    private val getCountry: GetCountry,
    override val uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    val textViewCurrencyTo: MutableLiveData<Currency> = MutableLiveData()
    val textViewCurrencyFrom: MutableLiveData<Currency> = MutableLiveData()
    val editTextConversionTo: MutableLiveData<String> = MutableLiveData("0$")
    val textViewRateConversion: MutableLiveData<String> = MutableLiveData()
    var textViewConversionFrom: MutableLiveData<String> = MutableLiveData()


    private val countryMutable = MutableLiveData<String>()

    val country: LiveData<String>
        get() = countryMutable

    private var _currencyList = MutableLiveData<List<Currency>>().apply {
        value = emptyList()
    }
    val currencyList: LiveData<List<Currency>> = _currencyList

    private var _historyList = MutableLiveData<List<HistoryItem>>().apply {
        value = emptyList()
    }
    val historyList: LiveData<List<HistoryItem>> = _historyList

    init {
        countryMutable.value = "..."
        initServiceCall()
    }

    private fun initServiceCall() {
        launch {
            when (val result = getCurrencies.invoke()) {
                is ResultData.Success -> {
                    _currencyList.value = result.data
                }
                is ResultData.Error -> {
                    result.exception.toString()
                }
            }
        }
    }

    fun getHistories() {
        launch {
            _currencyList.value?.let { currencyList ->
                _historyList.value = getHistories.invoke().map {
                    it.toHistoryItem(currencyList)
                }
            }
        }
    }

    fun onClickChange(from: String?, currency: Currency?) {

        if (from.isNullOrBlank() || currency == null) {
            return
        }

        val result = BigDecimal((from.toInt() * currency.value)).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        val history = History(0, Date(), getBaseCurrency(), currency.id, from.toDouble(), result)
        saveHistory(history)

        this.editTextConversionTo.value = result.toString()
    }

    private fun getBaseCurrency(): Int {
        _currencyList.value?.find { it.description.contains("USDUSD") }?.let { return it.id }
        return 1
    }

    private fun saveHistory(history: History) {
        launch {
            val result = saveHistory.invoke(history)
            //todo que significa el 1L ? el id del registro?
            if (result >= 1L) {
                getHistories()
            }
        }
    }

    fun updateLocation() {
        launch {
            countryMutable.value = getCountry.invoke()
        }
    }

    fun clickDataUp(item: HistoryItem) {
        textViewCurrencyFrom.value = _currencyList.value?.find {
            it.description == item.currencyFrom
        }
        textViewCurrencyTo.value = _currencyList.value?.find {
            it.description == item.currencyTo
        }
        textViewConversionFrom.value = item.valueFrom.toInt().toString()
        editTextConversionTo.value = item.valueTo.toString()
    }
}