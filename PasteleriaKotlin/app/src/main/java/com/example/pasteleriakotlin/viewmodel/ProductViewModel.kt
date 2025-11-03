package com.example.pasteleriakotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriakotlin.data.AppDatabase
import com.example.pasteleriakotlin.data.Product
import com.example.pasteleriakotlin.repository.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {
    private val repo: ProductRepository
    val products: StateFlow<List<Product>>

    init {
        val db = AppDatabase.getInstance(application)
        repo = ProductRepository(db.productDao())
        products = repo.all().map { it }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    fun insertProduct(name: String, price: Double, imageUri: String?) {
        viewModelScope.launch {
            repo.insert(Product(name = name, price = price, imageUri = imageUri))
        }
    }
}
