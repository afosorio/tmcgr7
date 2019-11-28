package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ConversionViewModelV2 : ViewModel() {


//    private var _serverResponse: MutableLiveData<LiveResponse> = MutableLiveData()
//    val serverResponse: LiveData<LiveResponse> = _serverResponse
//
//    var _currencyList = MutableLiveData<List<Currency>>().apply {
//        value = emptyList()
//    }
//    var currencyList: LiveData<List<Currency>> = _currencyList


    private var _currencyList2 = MutableLiveData<List<String>>().apply {
        value = emptyList()
    }
    var currencyList2: LiveData<List<String>> = _currencyList2

//    var textView: MutableLiveData<String> = MutableLiveData()

    init {
//        initServiceCall()
        //currencyList = currentRepository.getAll()

        viewModelScope.launch {
            delay(5000)
            initServiceCall()

        }
    }

    private fun initServiceCall() {
//        App. .getLive().observeForever {
//            it?.takeIf {
//                it.success
//            }?.let { response ->
//                successPath(response.quotes)
//            } ?: errorPath()
//        }


        var listademo = mutableListOf<String>()

        listademo.apply {
            add("11111111")
            add("11111111")
            add("11111111")
            add("11111111")
            add("11111111")
            add("11111111")
            add("11111111")
            add("11111111")
            add("11111111")
            add("11111111")
            add("11111111")
            add("11111111")
        }

        _currencyList2.value = listademo
    }

    private fun errorPath() {
    }

//    fun onCLick() {
//        initServiceCall()
//    }

//    private fun successPath(coins: MutableMap<String, Double>) {
//        saveData(coins)
//    }

//    private fun saveData(coins: MutableMap<String, Double>){
//
//        val test = mutableListOf<Currency>()
//        coins.forEach {
//
//            val objectToSave = Currency(
//                0,
//                it.key,
//                "https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjaqqf1qIvmAhXBv54KHQr_CcIQPAgH",
//                it.value
//            )
//            currentRepository.insert(objectToSave)
//            test.add(objectToSave)
//        }
//
//        _currencyList.value = test
//
//    }


}


