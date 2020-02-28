package com.grupo7.usecases

import com.grupo7.data.repository.HistoryRepository
import com.grupo7.domain.History

class SaveHistory(private val historyRepository: HistoryRepository) {

    suspend fun invoke(history: History) = historyRepository.saveHistory(history)
}
