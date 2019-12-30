package com.grupo7.moneychange.data.local.dao

import com.grupo7.moneychange.data.local.entity.Currency

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE

/**
 * afosorio 23.11.2019
 */
@Dao
interface CurrencyDao {

    @Insert
    fun insert(currency: Currency)

    @Insert(onConflict = REPLACE)
    fun insertCurrencies(currencies : List<Currency>)

    @Update
    fun update(vararg currency: Currency)

    @Delete
    fun delete(vararg currency: Currency)

    @Query("SELECT * FROM " + Currency.TABLE_NAME)
    fun getAll(): LiveData<List<Currency>>

    @Query("DELETE FROM " + Currency.TABLE_NAME)
    fun deleteAll()

}