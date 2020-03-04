package com.grupo7.data.repository

import com.delarosa.testshared.mockedHistory
import com.grupo7.data.source.LocalHistoryDataSource
import com.grupo7.domain.History
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class HistoryRepositoryTest {
    @Mock
    lateinit var localHistoryDataSource: LocalHistoryDataSource
    lateinit var historyRepository: HistoryRepository

    @Before
    fun setUp() {
        historyRepository = HistoryRepository(localHistoryDataSource)
    }

    @Test
    fun `get history List`() {
        runBlocking {
            val historyList = listOf(mockedHistory)
            whenever(localHistoryDataSource.getHistory()).thenReturn(historyList)
            val result = historyRepository.getHistory()
            Assert.assertEquals(historyList, result)
        }
    }

    @Test
    fun `get empty history List`() {
        runBlocking {
            val historyList = emptyList<History>()
            whenever(localHistoryDataSource.getHistory()).thenReturn(historyList)
            val result = historyRepository.getHistory()
            assertFalse(result.isNotEmpty())
        }
    }

    @Test
    fun `find history by Id`() {
        runBlocking {
            val history = mockedHistory.copy(2)
            whenever(localHistoryDataSource.findById(2)).thenReturn(history)
            val result = historyRepository.findById(2)
            assertEquals(history, result)
        }
    }

    @Test
    fun `saving history item`() {
        runBlocking {
            val history = mockedHistory.copy(1)
            whenever(localHistoryDataSource.saveHistory(history)).thenReturn(1L)
            val result=historyRepository.saveHistory(history)
            assertEquals(history.id,result.toInt())
        }
    }
}