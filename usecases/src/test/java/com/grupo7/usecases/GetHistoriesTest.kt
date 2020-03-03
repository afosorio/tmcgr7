package com.grupo7.usecases

import com.delarosa.testshared.mockedHistory
import com.grupo7.data.ResultData
import com.grupo7.data.repository.HistoryRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetHistoriesTest {


    @Mock
    lateinit var historyRepository: HistoryRepository

    lateinit var getHistories: GetHistories

    @Before
    fun setUp() {
        getHistories = GetHistories(historyRepository)
    }

    @Test
    fun `invoke calls history repository`() {
        runBlocking {
            val history = listOf(mockedHistory.copy(1))
            whenever(historyRepository.getHistory()).thenReturn(history)
            val result = getHistories.invoke()
            Assert.assertEquals(history, result)
        }
    }
}

