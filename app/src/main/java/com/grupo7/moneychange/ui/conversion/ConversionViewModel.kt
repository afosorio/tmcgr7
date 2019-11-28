package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo7.moneychange.data.entity.Currency
import com.grupo7.moneychange.data.repositories.LiveRepository
import com.grupo7.moneychange.models.response.LiveResponse
import com.grupo7.moneychange.repository.CurrencyRepository
import kotlinx.coroutines.launch

class ConversionViewModel(
    private val liveRepository: LiveRepository,
    private val currentRepository: CurrencyRepository
) : ViewModel() {


    private var _serverResponse: MutableLiveData<LiveResponse> = MutableLiveData()
    val serverResponse: LiveData<LiveResponse> = _serverResponse

    private var _currencyList = MutableLiveData<List<Currency>>().apply {
        value = emptyList()
    }
    var currencyList: LiveData<List<Currency>> = _currencyList

    var textView: MutableLiveData<String> = MutableLiveData()

    init {
        viewModelScope.launch {
            initServiceCall()
            getRoomInfo()
        }
    }
    private fun getRoomInfo(){
        currencyList = currentRepository.getAll()
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
        saveData(coins)
    }

    private fun saveData(coins: MutableMap<String, Double>) {

//        val listFromServer = mutableListOf<Currency>()
        coins.forEach {

            val objectToSave = Currency(
                0,
                it.key,
                "https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjaqqf1qIvmAhXBv54KHQr_CcIQPAgH",
                it.value
            )
            currentRepository.insert(objectToSave)
//            listFromServer.add(objectToSave)
        }

//        _currencyList.value = listFromServer

    }
}


