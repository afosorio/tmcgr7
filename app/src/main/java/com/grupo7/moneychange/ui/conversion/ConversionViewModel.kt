package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grupo7.moneychange.data.entity.Currency
import com.grupo7.moneychange.data.network.RetrofitLiveData
import com.grupo7.moneychange.data.repositories.LiveRepository
import com.grupo7.moneychange.models.response.LiveResponse
import com.grupo7.moneychange.repository.CurrencyRepository
import com.grupo7.moneychange.repository.HistoryRepository

class ConversionViewModel(private val liveRepository: LiveRepository, private val currentRepository: CurrencyRepository) : ViewModel() {


    private var _serverResponse : MutableLiveData<LiveResponse> = MutableLiveData()
    val serverResponse : LiveData<LiveResponse> = _serverResponse

    var textView : MutableLiveData<String> = MutableLiveData()

    init {
        initServiceCall()
    }

    private fun initServiceCall(){
        liveRepository.getLive().observeForever {
//            _serverResponse.value = it


//            DB _ new values
            val demoData = Currency(0, "","",1.0)
            currentRepository.insert(demoData)

        }

    }

    fun getLive() : RetrofitLiveData<LiveResponse> = liveRepository.getLive()

}


