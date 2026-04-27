package com.example.juegosfavoritos_practica5.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.juegosfavoritos_practica5.ui.welcome.screen.WelcomeScreen

@Composable
fun AppNavigation(paddingValues: PaddingValues) {
    val backStack = rememberNavBackStack(ScreenNav.Welcome)

    BackHandler(enabled = backStack.size > 1) {
        backStack.removeLastOrNull()
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        modifier = Modifier.padding(paddingValues),
        entryProvider = entryProvider {
            entry<ScreenNav.Welcome> {
                WelcomeScreen(onTimeOut = {
                    backStack.clear()
                })
            }
        })
}