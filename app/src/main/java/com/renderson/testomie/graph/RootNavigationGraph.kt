package com.renderson.testomie.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.renderson.testomie.ui.screen.HomComposable
import com.renderson.testomie.ui.screen.SellComposable

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        composable(route = Graph.HOME) {
            HomComposable {
                navController.navigate(Graph.SELL)
            }
        }
        composable(route = Graph.SELL) {
            SellComposable {
                navController.popBackStack()
            }
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val SELL = "sell_graph"
}