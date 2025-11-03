package com.example.pasteleriakotlin.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY id DESC")
    fun all(): Flow<List<Product>>

    @Insert
    suspend fun insert(product: Product)
}
