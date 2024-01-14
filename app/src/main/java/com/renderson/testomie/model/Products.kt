package com.renderson.testomie.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Products(
    @PrimaryKey(autoGenerate = true)
    val productId: Int? = 0,
    var name: String? = null,
    var amount: String = "",
    var unitaryValue: String = "",
) {
    val valorTotal: Double
        get() = if (amount.isNotEmpty() && unitaryValue.isNotEmpty()) {
            amount.toDouble() * unitaryValue.toDouble()
        } else 0.0
}
