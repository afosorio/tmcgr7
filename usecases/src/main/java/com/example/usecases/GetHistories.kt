package com.example.usecases

import com.example.data.repository.HistoryRepository
import com.example.domain.History

class GetHistories(private val historyRepository: HistoryRepository) {
    suspend fun invoke(): List<History> = historyRepository.getHistory()
}