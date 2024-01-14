package com.renderson.testomie.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renderson.testomie.model.Products
import com.renderson.testomie.util.OutlinedTextFieldComposable
import com.renderson.testomie.util.formatForBrazilianCurrency

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun BuyComposable(onClick: () -> Unit) {
    var productList by remember {
        mutableStateOf(value = emptyList<Products>())
    }

    var name by remember {
        mutableStateOf(value = "")
    }

    var amount by remember {
        mutableStateOf(value = "")
    }

    var unitaryValue by remember {
        mutableStateOf(value = "")
    }

    var valueTotal by remember {
        mutableStateOf(value = "")
    }

    var isIncludeButtonClicked by remember {
        mutableStateOf(value = false)
    }

    var enabledButton by remember {
        mutableStateOf(true)
    }

    enabledButton = name.isNotEmpty() && amount.isNotEmpty() && unitaryValue.isNotEmpty()

    fun updateValueTotal() {
        valueTotal = if (amount.isNotEmpty() && unitaryValue.isNotEmpty()) {
            (amount.toDouble() * unitaryValue.toDouble()).toString()
        } else { "" }
    }

    fun clearFields() {
        name = ""
        amount = ""
        unitaryValue = ""
        valueTotal = ""
    }

    LaunchedEffect(isIncludeButtonClicked) {
        if (isIncludeButtonClicked) {
            clearFields()
        }
    }
    
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = "Produto",
                    style = MaterialTheme.typography.titleLarge
                )
                OutlinedTextFieldComposable(
                    input = mutableStateOf(name),
                    label = "Nome do produto",
                    imeAction = ImeAction.Next
                ) {
                    name = it
                }
                OutlinedTextFieldComposable(
                    input = mutableStateOf(amount),
                    label = "Quantidade",
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.NumberPassword
                ) {
                    amount = it
                    updateValueTotal()
                }
                OutlinedTextFieldComposable(
                    input = mutableStateOf(unitaryValue),
                    label = "Valor unitário",
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.NumberPassword
                ) {
                    unitaryValue = it
                    updateValueTotal()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(4.dp)
                        )
                ){
                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = "Valor total do item R$ $valueTotal"
                    )
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    enabled = enabledButton,
                    onClick = {
                        val newProduct = Products(name, amount, unitaryValue)
                        productList = productList + newProduct
                        isIncludeButtonClicked = true
                    }
                ) {
                    Text(text = "Incluir")
                }
                Spacer(modifier = Modifier.padding(top = 8.dp))
                TableProducts(products = productList)
                Column (
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    var totalItems = 0
                    var total = 0.0
                    productList.forEach {
                        totalItems += it.amount.toInt()
                        total += it.valorTotal
                    }
                    Text(text = "Qtd. total de itens: $totalItems")
                    Text(text = "Qtd. total do pedido: ${total.formatForBrazilianCurrency()}")
                }
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                )
                Column (
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        enabled = productList.isNotEmpty(),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(
                            text = "Salvar"
                        )
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Red
                        ),
                        border = BorderStroke(1.dp, Color.Red),
                        onClick = { onClick.invoke() }
                    ) {
                        Text(
                            text = "Cancelar"
                        )
                    }
                }
            }
        }
    )
}


@Preview
@Composable
fun PreviewBuyComposable() {
    BuyComposable {}
}