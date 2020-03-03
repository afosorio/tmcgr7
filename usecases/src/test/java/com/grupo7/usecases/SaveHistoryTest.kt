package com.grupo7.usecases

import com.delarosa.testshared.mockedHistory
import com.grupo7.data.repository.HistoryRepository
import com.grupo7.domain.History
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveHistoryTest {
    @Mock
    lateinit var historyRepository: HistoryRepository
    lateinit var saveHistory: SaveHistory

    @Before
    fun setup() {
        saveHistory = SaveHistory(historyRepository)
    }

    @Test
    fun `validate saved id`() {
        runBlocking {
            val history = mockedHistory.copy(1)
            whenever(historyRepository.saveHistory(history)).thenReturn(1L)
            val result = saveHistory.invoke(history)
            Assert.assertEquals(history.id, result.toInt())
        }
    }

}
