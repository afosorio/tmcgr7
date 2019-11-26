package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grupo7.moneychange.data.network.RetrofitLiveData
import com.grupo7.moneychange.data.repositories.LiveRepository
import com.grupo7.moneychange.models.response.LiveResponse

class ConversionViewModel(private val liveRepository: LiveRepository) : ViewModel() {

    private var _serverResponse : MutableLiveData<RetrofitLiveData<LiveResponse>> = MutableLiveData()
    val serverResponse : LiveData<RetrofitLiveData<LiveResponse>> = _serverResponse

    var textView : MutableLiveData<String> = MutableLiveData()


    init {
       _serverResponse.value = initServiceCall()
    }

    private fun initServiceCall() = liveRepository.getLive()

}


