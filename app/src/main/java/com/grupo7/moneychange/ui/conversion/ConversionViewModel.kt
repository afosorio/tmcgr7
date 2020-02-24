package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo7.data.repository.ResultData
import com.grupo7.domain.Currency
import com.grupo7.domain.History
import com.grupo7.moneychange.data.repository.CountryRepository
import com.grupo7.moneychange.data.repository.local.HistoryRepository
import com.grupo7.moneychange.utils.PermissionChecker
import com.grupo7.usecases.GetCurrencies
import com.grupo7.usecases.GetHistories
import kotlinx.coroutines.launch
import java.util.*

class ConversionViewModel(

    private val getCurrencies: GetCurrencies,
    private val historyRepository: HistoryRepository,
    private val countryRepository: CountryRepository,
    //private val getCurrencies: GetCurrencies,
    private val getHistories: GetHistories

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
    val currencyList: LiveData<List<Currency>>
        get() = _currencyList

    private var _historyList = MutableLiveData<List<History>>().apply {
        value = emptyList()
    }
    val historyList: LiveData<List<History>>
        get() = _historyList

    init {
        initServiceCall()
        initHistory()
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

    private fun initHistory() {
        viewModelScope.launch {
            _historyList.postValue(getHistories.invoke())
        }
    }

    fun onClickChange(from: String?, currency: Currency?) {

        if (from.isNullOrBlank() || currency == null) {
            return
        }

        val result = (from.toInt() * currency.value)
        val history = History(0, Date(), 1, currency.id, from.toDouble(), result)
        saveHistory(history)

        this.editTextConversionTo.value = result.toString()
        this.textViewConversionFrom.value = ""
    }

    private fun saveHistory(history: History) {
        viewModelScope.launch {
            //historyRepository.insert(history)
        }
    }

    fun getLocation(permissionChecker: PermissionChecker) {
        viewModelScope.launch {
            countryMutable.value = countryRepository.getCountryLocation(permissionChecker)
        }
    }

    fun clickDataUp(item: History) {
        //TODO("se debe actualizar el spinner de la moneda seleccionada")
        textViewConversionFrom.value = item.valueFrom.toInt().toString()
        editTextConversionTo.value = item.valueTo.toString()
    }
}