package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo7.moneychange.data.entity.Currency
import com.grupo7.moneychange.data.entity.History
import com.grupo7.moneychange.data.repositories.CountryRepository
import com.grupo7.moneychange.data.repositories.LiveRepository
import com.grupo7.moneychange.repository.CurrencyRepository
import com.grupo7.moneychange.repository.HistoryRepository
import com.grupo7.moneychange.utils.PermissionChecker
import kotlinx.coroutines.launch

class ConversionViewModel(

    private val liveRepository: LiveRepository,
    private val currentRepository: CurrencyRepository,
    private val historyRepository: HistoryRepository,
    private val countryRepository: CountryRepository

) : ViewModel() {

    val textViewCurrency: MutableLiveData<Currency> = MutableLiveData()
    val editTextConversionTo: MutableLiveData<String> = MutableLiveData()
    val textViewRateConversion: MutableLiveData<String> = MutableLiveData()
    val textViewConversionFrom: MutableLiveData<String> = MutableLiveData()

    private val countryMutable = MutableLiveData<String>()
    val country: LiveData<String> get() = countryMutable

    private var _currencyList = MutableLiveData<List<Currency>>().apply {
        value = emptyList()
    }
    val currencyList: LiveData<List<Currency>> get() = _currencyList

    private var _historyList = MutableLiveData<List<History>>().apply {
        value = emptyList()
    }
    val historyList: LiveData<List<History>> get() = _historyList

    init {
        initRoom()
        initHistory()
        initServiceCall()

    }

    private fun initServiceCall() {
        viewModelScope.launch {
            liveRepository.getLive().observeForever {
                it?.takeIf {
                    it.success
                }?.let { response ->
                    successPath(response.quotes)
                } ?: errorPath()
            }
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

            val objectToSave = Currency(
                0,
                it.key.substring(3, 6),
                "https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjaqqf1qIvmAhXBv54KHQr_CcIQPAgH",
                it.value
            )
            listFromServer.add(objectToSave)
        }
        currentRepository.insertCurrencyList(listFromServer)
    }

    private fun initRoom() {
        currentRepository.getAll().observeForever {
            _currencyList.value = it
        }
    }

    fun onClickChange(textViewConversionFrom: String?, textViewCurrency: Currency?) {
        if (textViewConversionFrom.isNullOrBlank() || textViewCurrency == null) {
            return
        }

        val result = (textViewConversionFrom.toInt() * textViewCurrency.value)

        this.editTextConversionTo.value = result.toString()
        saveHistory(
            History(
                0,
                1,
                textViewCurrency.id,
                textViewConversionFrom.toDouble(),
                result
            )
        )
    }

    private fun saveHistory(history: History) {
        historyRepository.insert(history)
    }

    private fun initHistory() {
        historyRepository.getAll().observeForever {
            _historyList.value = it
        }
    }

    fun getLocation(permissionChecker: PermissionChecker) {
        viewModelScope.launch {
            countryMutable.value = countryRepository.getCountryLocation(permissionChecker)
        }
    }

    fun clickDataUp(item: History) {
        textViewConversionFrom.value = item.valueFrom.toInt().toString()
        editTextConversionTo.value = item.valueTo.toString()
    }
}