package com.grupo7.moneychange.data.dao

import com.grupo7.moneychange.data.entity.Currency

import androidx.lifecycle.LiveData
import androidx.room.*
/**
 * afosorio 23.11.2019
 */
@Dao
interface CurrencyDao {

    @Insert
    fun insert(currency: Currency)

    @Update
    fun update(vararg currency: Currency)

    @Delete
    fun delete(vararg currency: Currency)

    @Query("SELECT * FROM " + Currency.TABLE_NAME)
    fun getAll(): LiveData<List<Currency>>
}