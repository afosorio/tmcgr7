package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grupo7.moneychange.data.repositories.CountryRepository
import com.grupo7.moneychange.data.repositories.LiveRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConversionViewModel(private val liveRepository: LiveRepository, private val countryRepository: CountryRepository) : ViewModel() {

    private val countryMutable = MutableLiveData<String>()
    val country: LiveData<String> get() = countryMutable

    init {
        GlobalScope.launch(Dispatchers.Main) {
            val countryRepository = countryRepository.getCountryLocation()
            val text = "estás en $countryRepository"
            countryMutable.value = text
        }
    }

    fun getLive() = liveRepository.getLive()


}


