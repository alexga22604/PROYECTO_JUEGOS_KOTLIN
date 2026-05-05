package com.example.juegosfavoritos_practica5.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.juegosfavoritos_practica5.R
import com.example.juegosfavoritos_practica5.ui.common.BottomBar
import com.example.juegosfavoritos_practica5.ui.game.screen.AddGameScreen
import com.example.juegosfavoritos_practica5.ui.game.screen.GameScreen
import com.example.juegosfavoritos_practica5.ui.game.viewmodel.GameViewModel
import com.example.juegosfavoritos_practica5.ui.preference.screen.PreferenceScreen
import com.example.juegosfavoritos_practica5.ui.studio.screen.StudioGamesScreen
import com.example.juegosfavoritos_practica5.ui.welcome.screen.WelcomeScreen

@Composable
fun AppNavigation(
    paddingValues: PaddingValues,
    backStack: NavBackStack<NavKey>,
    viewModel: GameViewModel
) {
    val gamesRoute = stringResource(R.string.games_route)
    val profileRoute = stringResource(R.string.profile_route)
    val currentKey = backStack.lastOrNull()

    val currentRouteStr = when (currentKey) {
        is ScreenNav.Games, is ScreenNav.Studio -> gamesRoute
        is ScreenNav.Profile -> profileRoute
        else -> null
    }

    val displayModifier = if (currentKey is ScreenNav.Studio) {
        Modifier
    } else {
        Modifier.padding(paddingValues)
    }

    BackHandler(enabled = backStack.size > 1) {
        backStack.removeLastOrNull()
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        modifier = displayModifier,
        entryProvider = entryProvider {
            entry<ScreenNav.Welcome> {
                WelcomeScreen(onTimeOut = {
                    backStack.clear()
                    backStack.add(ScreenNav.Games)
                })
            }
            entry<ScreenNav.Games> {
                GameScreen(
                    viewModel = viewModel,
                    onNavigateToAddGame = { backStack.add(ScreenNav.AddGame) },
                    onNavigateToStudio = { name ->
                        backStack.add(ScreenNav.Studio(name = name))
                    }
                )
            }
            entry<ScreenNav.Profile> {
                PreferenceScreen(viewModel = viewModel)
            }
            entry<ScreenNav.AddGame> {
                AddGameScreen(
                    viewModel = viewModel,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
            entry<ScreenNav.Studio> { key ->
                StudioGamesScreen(
                    studioName = key.name,
                    viewModel = viewModel,
                    onBack = { backStack.removeLastOrNull() },
                    bottomBar = {
                        BottomBar(
                            currentRoute = currentRouteStr,
                            onNavigate = { }
                        )
                    }
                )
            }
        }
    )
}