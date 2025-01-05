package com.example.foodicsassessment.features.home_tabs.presentation

import BottomNavigationBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodicsassessment.core.CommonUiEffect
import com.example.foodicsassessment.features.home_tabs.navigation.MenuRoute
import com.example.foodicsassessment.features.home_tabs.navigation.OrdersRoute
import com.example.foodicsassessment.features.home_tabs.navigation.SettingsRoute
import com.example.foodicsassessment.features.home_tabs.navigation.TableRoute
import com.example.foodicsassessment.features.menu.MenuScreenRoot
import com.example.foodicsassessment.features.orders.OrderScreenRoot
import com.example.foodicsassessment.features.settings.SettingsScreenRoot
import com.example.foodicsassessment.features.tables.presentation.TablesScreenRoot

@Composable
fun HomeTabsScreenRoot(
    handleCommonEvents: (CommonUiEffect) -> Unit,
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar =
        { BottomNavigationBar(navController = navController) }
    ) { contentPadding ->

        NavHost(navController, startDestination = TableRoute) {
            composable<TableRoute> {
                TablesScreenRoot(
                    modifier = Modifier.padding(contentPadding),
                    handleCommonEvents = handleCommonEvents
                )
            }
            //orders route
            composable<OrdersRoute> {
                OrderScreenRoot()
            }
            //menu route
            composable<MenuRoute> {
                MenuScreenRoot()
            }
            //settings route
            composable<SettingsRoute> {
                SettingsScreenRoot()
            }
        }
    }
}
