package com.grupo7.usecases

import com.grupo7.data.repository.HistoryRepository
import com.grupo7.domain.History

class GetHistories(private val historyRepository: HistoryRepository) {
    suspend fun invoke(): List<History> = historyRepository.getHistory()
}