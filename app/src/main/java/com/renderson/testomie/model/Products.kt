package com.renderson.testomie.model

data class Products(
    var name: String? = null,
    var amount: String = "",
    var unitaryValue: String = "",
) {
    var valorTotal: Double = 0.0
        get() = if (amount.isNotEmpty() && unitaryValue.isNotEmpty()) {
            amount.toDouble() * unitaryValue.toDouble()
        } else 0.0
}
