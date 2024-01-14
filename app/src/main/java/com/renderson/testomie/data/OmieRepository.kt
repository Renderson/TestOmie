package com.renderson.testomie.data

import com.renderson.testomie.model.Products
import javax.inject.Inject

class OmieRepository @Inject constructor(private val omieDao: OmieDao) {

    suspend fun getAllSales(): List<Products> {
        return omieDao.getAllSales()
    }

    suspend fun insertSale(products: List<Products>) {
        return omieDao.insertSale(products = products)
    }
}