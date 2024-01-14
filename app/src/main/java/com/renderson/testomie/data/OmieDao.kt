package com.renderson.testomie.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.renderson.testomie.model.Products

@Dao
interface OmieDao {
    @Query("SELECT * FROM products")
    suspend fun getAllSales(): List<Products>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSale(products: List<Products>)
}