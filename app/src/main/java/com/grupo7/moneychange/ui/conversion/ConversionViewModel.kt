package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo7.data.repository.CountryRepository
import com.grupo7.data.ResultData
import com.grupo7.domain.Currency
import com.grupo7.domain.History
import com.grupo7.usecases.GetCurrencies
import com.grupo7.usecases.GetHistories
import com.grupo7.usecases.SaveHistory
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class ConversionViewModel(
    private val getCurrencies: GetCurrencies,
    private val countryRepository: CountryRepository,
    private val getHistories: GetHistories,
    private val saveHistory: SaveHistory

) : ViewModel() {

    val textViewCurrency: MutableLiveData<Currency> = MutableLiveData()
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

    private var _historyList = MutableLiveData<List<History>>().apply {
        value = emptyList()
    }
    val historyList: LiveData<List<History>> = _historyList

    init {
        initServiceCall()
        getHistories()
    }

    private fun initServiceCall() {
        viewModelScope.launch {
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

    private fun getHistories() {
        viewModelScope.launch {
            _historyList.value = getHistories.invoke()
        }
    }

    fun onClickChange(from: String?, currency: Currency?) {

        if (from.isNullOrBlank() || currency == null) {
            return
        }

        val result = BigDecimal((from.toInt() * currency.value)).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        val history = History(0, Date(), 1, currency.id, from.toDouble(), result)
        saveHistory(history)

        this.editTextConversionTo.value = result.toString()
    }

    private fun saveHistory(history: History) {
        viewModelScope.launch {
            val result = saveHistory.invoke(history)
            if (result >= 1L) {
                getHistories()
            }
        }
    }

    fun getLocation() {
        viewModelScope.launch {
            countryMutable.value = countryRepository.getCountryLocation()
        }
    }

    fun clickDataUp(item: History) {
        //TODO("se debe actualizar el spinner de la moneda seleccionada")
        textViewConversionFrom.value = item.valueFrom.toInt().toString()
        editTextConversionTo.value = item.valueTo.toString()
    }
}