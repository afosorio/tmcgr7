package com.grupo7.moneychange.ui.conversion

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo7.moneychange.data.entity.Currency
import com.grupo7.moneychange.data.entity.History
import com.grupo7.moneychange.data.repositories.LiveRepository
import com.grupo7.moneychange.repository.CurrencyRepository
import com.grupo7.moneychange.repository.HistoryRepository
import kotlinx.coroutines.launch

class ConversionViewModel(

    private val liveRepository: LiveRepository,
    private val currentRepository: CurrencyRepository,
    private val historyRepository: HistoryRepository

) : ViewModel() {

    var textViewCuurency:  MutableLiveData<Currency> = MutableLiveData()
    var editTextConversionTo: MutableLiveData<String> = MutableLiveData()
    var textViewConversionFrom: MutableLiveData<String> = MutableLiveData()
    var textViewRateConversion: MutableLiveData<String> = MutableLiveData()

    private var _currencyList = MutableLiveData<List<Currency>>().apply {
        value = emptyList()
    }
    var currencyList: LiveData<List<Currency>> = _currencyList

    private var _historyList = MutableLiveData<List<History>>().apply {
        value = emptyList()
    }
    var historyList: LiveData<List<History>> = _historyList

    init {
        viewModelScope.launch {
            initServiceCall()
        }
        getHistory()
    }

    private fun initServiceCall() {
        liveRepository.getLive().observeForever {
            it?.takeIf {
                it.success
            }?.let { response ->
                successPath(response.quotes)
            } ?: errorPath()
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
                it.key.substring(3,6),
                "https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjaqqf1qIvmAhXBv54KHQr_CcIQPAgH",
                it.value
            )
            listFromServer.add(objectToSave)
        }
        currentRepository.insertCurrencyList(listFromServer)
        getRoomInfo()
    }

    private fun getRoomInfo(){
        currentRepository.getAll().observeForever {
            _currencyList.value  = it
        }
    }

    fun onClickChange(view: View) {

        editTextConversionTo.value = (textViewConversionFrom.value!!.toInt()
                * textViewCuurency.value!!.value.toInt()).toString()

        var history = History(
            0,
            1,
            textViewCuurency.value!!.id,
            textViewConversionFrom.value!!.toDouble(),
            editTextConversionTo.value!!.toDouble()
        )
        saveHistory(history)
    }

    private fun saveHistory(history:History){
        historyRepository.insert(history)
        getHistory()
    }

    private fun getHistory(){
        historyRepository.getAll().observeForever {
            _historyList.value = it
        }
    }
}