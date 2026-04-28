package com.example.juegosfavoritos_practica5.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.juegosfavoritos_practica5.ui.game.screen.GameScreen
import com.example.juegosfavoritos_practica5.ui.game.viewmodel.GameViewModel
import com.example.juegosfavoritos_practica5.ui.preference.screen.PreferenceScreen
import com.example.juegosfavoritos_practica5.ui.welcome.screen.WelcomeScreen

@Composable
fun AppNavigation(
    paddingValues: PaddingValues,
    backStack: NavBackStack<NavKey>,
    viewModel: GameViewModel
) {
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
                    backStack.add(ScreenNav.Games)
                })
            }

            entry<ScreenNav.Games> {
                GameScreen(viewModel = viewModel)
            }

            entry<ScreenNav.Profile> {
                PreferenceScreen(viewModel = viewModel)
            }
        }
    )
}