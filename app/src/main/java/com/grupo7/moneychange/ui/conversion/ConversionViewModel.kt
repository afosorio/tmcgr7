package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo7.moneychange.data.local.entity.History
import com.grupo7.moneychange.data.mappers.toModelCurrency
import com.grupo7.moneychange.data.network.ResultData
import com.grupo7.moneychange.data.repository.CountryRepository
import com.grupo7.moneychange.data.repository.local.HistoryRepository
import com.grupo7.moneychange.ui.model.Currency
import com.grupo7.moneychange.usecases.GetAllExchangeRateData
import com.grupo7.moneychange.utils.PermissionChecker
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*


class ConversionViewModel(

    private val getAllExchangeRateData: GetAllExchangeRateData,
    private val historyRepository: HistoryRepository,
    private val countryRepository: CountryRepository

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
            when (val result = getAllExchangeRateData.invoke()) {
                is ResultData.Success -> {
                    _currencyList.value = result.data.toModelCurrency()
                }
                is ResultData.Error -> {
                }
            }
        }
    }


    private fun initHistory() {
        historyRepository.getAll().observeForever {
            _historyList.value = it
        }
    }

    fun onClickChange(from: String?, currency: Currency?) {

        if (from.isNullOrBlank() || currency == null) {
            return
        }

        val result = BigDecimal((from.toInt() * currency.value)).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        val history = History(0, Date(), "USD", currency.description, from.toDouble(), result)
        saveHistory(history)

        this.editTextConversionTo.value = result.toString()
    }

    private fun saveHistory(history: History) {
        viewModelScope.launch {
            historyRepository.insert(history)
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