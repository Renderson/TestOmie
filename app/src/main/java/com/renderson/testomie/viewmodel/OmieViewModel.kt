package com.renderson.testomie.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.renderson.testomie.data.OmieRepository
import com.renderson.testomie.model.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OmieViewModel @Inject constructor(
    private val repository: OmieRepository
): ViewModel() {

    private val _totalSales = mutableStateOf(0.0)
    val totalSales: State<Double> = _totalSales

    suspend fun getAllSales() {
        var total = 0.0
        val sales = repository.getAllSales()
        sales.forEach { product ->
            total += product.valorTotal
        }
        _totalSales.value = total
    }

    suspend fun insertSales(products: List<Products>) {
        repository.insertSale(products = products)
    }
}