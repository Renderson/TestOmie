package com.renderson.testomie.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomComposable(
    onClick: () -> Unit
) {
    Scaffold(
        content =  {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = { onClick.invoke() }
                ) {
                    Text(
                        text = "Vender"
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Total de vendas: R$ 1.000,00",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    )
}

@Preview
@Composable
private fun PreviewHomeComposable() {
    HomComposable {}
}