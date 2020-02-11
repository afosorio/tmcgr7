package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Currency
import com.example.domain.History
import com.example.usecases.GetCurrencies
import com.example.usecases.GetHistories
import com.grupo7.moneychange.repository.CountryRepository
import com.grupo7.moneychange.repository.local.HistoryRepository
import com.grupo7.moneychange.repository.network.LiveRepository
import com.grupo7.moneychange.utils.PermissionChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ConversionViewModel(

    private val liveRepository: LiveRepository,
    private val historyRepository: HistoryRepository,
    private val countryRepository: CountryRepository,
    private val getCurrencies: GetCurrencies,
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
        initCurrency()
        initHistory()
    }

    private fun initServiceCall() {

        liveRepository.getLive().observeForever {
            it?.takeIf {
                it.success
            }?.let { response ->
                successPath(coins = response.quotes)
            } ?: errorPath()
        }
    }

    private fun initCurrency() {
        viewModelScope.launch {
            _currencyList.postValue(getCurrencies.invoke())
        }
    }

    private fun initHistory() {
        viewModelScope.launch {
            _historyList.postValue(getHistories.invoke())
        }
    }

    private fun errorPath() {
    }

    private fun successPath(coins: MutableMap<String, Double>) {
        saveCurrencyList(coins)
    }

    private fun saveCurrencyList(coins: MutableMap<String, Double>) {

        val listFromServer = mutableListOf<Currency>()
        coins.forEach {
            val objectToSave = Currency(0, it.key.substring(3, 6), "", it.value)
            listFromServer.add(objectToSave)
        }
        viewModelScope.launch(Dispatchers.IO) {

            currentRepository.insertCurrencyList(listFromServer)
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