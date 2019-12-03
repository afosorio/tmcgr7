package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grupo7.moneychange.data.repositories.CountryRepository
import com.grupo7.moneychange.data.repositories.LiveRepository
import com.grupo7.moneychange.utils.PermissionChecker
import kotlinx.coroutines.*

class ConversionViewModel(private val liveRepository: LiveRepository, private val countryRepository: CountryRepository) : ViewModel() {

    private val countryMutable = MutableLiveData<String>()
    val country: LiveData<String> get() = countryMutable

    fun getLocation(permissionChecker: PermissionChecker) {
        CoroutineScope(Dispatchers.IO).launch {
            val countryRepository = countryRepository.getCountryLocation(permissionChecker)
            val text = "estás en $countryRepository"
            withContext(Dispatchers.Main) { countryMutable.value = text }
        }
    }

    fun getLive() = liveRepository.getLive()


}


