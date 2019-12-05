package com.grupo7.moneychange.ui.conversion

import androidx.lifecycle.ViewModel
import com.grupo7.moneychange.data.repositories.LiveRepository

class ConversionViewModel(private val liveRepository: LiveRepository) : ViewModel() {

    fun getLive() = liveRepository.getLive()


}


