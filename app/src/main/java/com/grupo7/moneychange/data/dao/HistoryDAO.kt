package com.grupo7.moneychange.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.grupo7.moneychange.data.entity.History

/**
 * afosorio 23.11.2019
 */
@Dao
interface HistoryDao {

    @Insert
    fun insert(history: History)

    @Update
    fun update(vararg history: History)

    @Delete
    fun delete(vararg history: History)

    @Query("SELECT * FROM " + History.TABLE_NAME )
    fun getAll(): LiveData<List<History>>
}