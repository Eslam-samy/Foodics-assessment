package com.example.foodicsassessment.core.presentation

import FoodicsAppNavigation
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foodicsassessment.core.CommonUiEffect
import com.example.foodicsassessment.core.UiText
import com.example.foodicsassessment.core.presentation.ui.theme.MyApplicationTheme
import com.example.foodicsassessment.core.presentation.util.navigateTo


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                FoodicsAppNavigation(
                    navController = navController,
                    handleCommonEvents = { event: CommonUiEffect ->
                        handleCommonEvents(event, navController)
                    }
                )
            }
        }
    }

    private fun handleCommonEvents(event: CommonUiEffect, navController: NavHostController) {
        when (event) {
            is CommonUiEffect.Navigate -> {
                navController.navigateTo(
                    event.route,
                    event.popUpTo,
                    event.inclusive,
                    event.singleTop
                )
            }

            CommonUiEffect.NavigateUp -> navController.navigateUp()
            is CommonUiEffect.ShowDialog -> {}
            is CommonUiEffect.ShowToast -> showToast(event.uiText)
            is CommonUiEffect.ShowUnauthenticatedState -> {
                showToast(event.uiText)
//                navController.navigateTo(WelcomeScreen, HomeTabsScreen, true)
            }
        }
    }

    private fun showToast(uiText: UiText) {
        Toast.makeText(
            this,
            uiText.asString(this),
            Toast.LENGTH_LONG
        ).show()
    }
}

