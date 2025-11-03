package com.example.pasteleriakotlin.repository

import com.example.pasteleriakotlin.data.Product
import com.example.pasteleriakotlin.data.ProductDao
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val dao: ProductDao) {
    fun all(): Flow<List<Product>> = dao.all()
    suspend fun insert(product: Product) = dao.insert(product)
}
