package com.grupo7.moneychange.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.grupo7.moneychange.data.local.entity.History

/**
 * afosorio 23.11.2019
 */
@Dao
interface HistoryDao {

    @Insert
    fun insert(history: History)

    @Update
    fun update(vararg history: History)

    @Query("SELECT * FROM HISTORY WHERE id = :id")
    fun findById(id: Int): History

    @Delete
    fun delete(vararg history: History)

//    @Query("SELECT * FROM History" )
//    fun getAll(): List<History>

    @Query("SELECT * FROM HISTORY")

    fun getAll(): LiveData<List<History>>
}