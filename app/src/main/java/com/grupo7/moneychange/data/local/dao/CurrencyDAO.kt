package com.grupo7.moneychange.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.grupo7.moneychange.data.local.entity.Currency
@Dao
abstract class CurrencyDao : BaseDao<Currency> {

    @Query("SELECT * FROM Currency")
    abstract fun getAll(): List<Currency>

    @Query("DELETE FROM Currency")
    abstract fun deleteAll()

    @Query("SELECT COUNT(id) FROM CURRENCY")
    abstract fun currencyCount(): Int

    @Query("SELECT * FROM CURRENCY WHERE id = :id")
    abstract fun findById(id: Int): Currency

    @Insert(onConflict = REPLACE)
    abstract fun insert(currencies: List<Currency>)

}