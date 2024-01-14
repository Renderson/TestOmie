package com.renderson.testomie.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.renderson.testomie.model.Products
import com.renderson.testomie.util.formatForBrazilianCurrency

@Composable
fun TableProducts(products: List<Products>) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        Text(
                            text = "Nome",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyMedium,
                            color = contentColorFor(MaterialTheme.colorScheme.background)
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Quantidade",
                                style = MaterialTheme.typography.bodyMedium,
                                color = contentColorFor(MaterialTheme.colorScheme.background)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Valor Unitário",
                                style = MaterialTheme.typography.bodyMedium,
                                color = contentColorFor(MaterialTheme.colorScheme.background)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Valor Total",
                                style = MaterialTheme.typography.bodyMedium,
                                color = contentColorFor(MaterialTheme.colorScheme.background)
                            )
                        }
                    }
                }

                items(products) { produto ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(MaterialTheme.colorScheme.background),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = produto.name ?: "",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodySmall,
                            color = contentColorFor(MaterialTheme.colorScheme.background)
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = produto.amount,
                                style = MaterialTheme.typography.bodySmall,
                                color = contentColorFor(MaterialTheme.colorScheme.background)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = produto.unitaryValue.toDouble().formatForBrazilianCurrency(),
                                style = MaterialTheme.typography.bodySmall,
                                color = contentColorFor(MaterialTheme.colorScheme.background)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = produto.valorTotal.formatForBrazilianCurrency(),
                                style = MaterialTheme.typography.bodySmall,
                                color = contentColorFor(MaterialTheme.colorScheme.background)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTableProducts() {
    val products = listOf(
        Products("Produto A", "5", "10.0"),
        Products("Produto B", "3", "15.0"),
        Products("Produto C", "2", "20.0")
    )

    TableProducts(products = products)
}