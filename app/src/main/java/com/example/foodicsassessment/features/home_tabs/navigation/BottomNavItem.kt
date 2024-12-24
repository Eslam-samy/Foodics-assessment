package com.example.foodicsassessment.features.home_tabs.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.foodicsassessment.R

sealed class BottomNavItem<T>(
    val route: T,
    val icon: ImageVector,
    val title:Int,
) {
    data object Tables : BottomNavItem<TableRoute>(
        TableRoute,
        Icons.Default.Restaurant,
        title = R.string.tables
    )

    data object Orders :
        BottomNavItem<OrdersRoute>(
            OrdersRoute,
            Icons.AutoMirrored.Default.MenuBook,
            title = R.string.orders
        )

    data object Menu :
        BottomNavItem<MenuRoute>(
            MenuRoute,
            Icons.Default.LocalDining,
            title = R.string.menu
        )

    data object Settings :
        BottomNavItem<SettingsRoute>(
            SettingsRoute,
            Icons.Default.Settings,
            title = R.string.settings
        )


}

