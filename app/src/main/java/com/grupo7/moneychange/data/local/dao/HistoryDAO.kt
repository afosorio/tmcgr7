package com.grupo7.moneychange.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.grupo7.moneychange.data.local.entity.History

/**
 * afosorio 23.11.2019
 */
@Dao
abstract class HistoryDao : BaseDao<History> {

    @Query("SELECT * FROM History WHERE id = :id")
    abstract fun findById(id: Int): History

    @Query("SELECT * FROM History")
    abstract fun getAll(): List<History>
}