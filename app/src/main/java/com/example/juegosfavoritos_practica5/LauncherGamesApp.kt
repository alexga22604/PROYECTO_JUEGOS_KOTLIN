package com.example.juegosfavoritos_practica5

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.juegosfavoritos_practica5.ui.common.AppScaffold
import com.example.juegosfavoritos_practica5.ui.game.component.FilterDialog
import com.example.juegosfavoritos_practica5.ui.game.component.GameTopBar
import com.example.juegosfavoritos_practica5.ui.game.viewmodel.GameViewModel
import com.example.juegosfavoritos_practica5.ui.navigation.AppNavigation
import com.example.juegosfavoritos_practica5.ui.navigation.ScreenNav

private const val DEFAULT = ""

@Composable
fun LauncherGamesApp(viewModel: GameViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    val backStack = rememberNavBackStack(ScreenNav.Welcome)
    val currentRoute = backStack.lastOrNull()
    var showFilterDialog by remember { mutableStateOf(false) }
    val gamesRoute = stringResource(R.string.games_route)
    val profileRoute = stringResource(R.string.profile_route)
    val currentRouteString = when (currentRoute) {
        ScreenNav.Games -> gamesRoute
        ScreenNav.Profile -> profileRoute
        else -> DEFAULT
    }

    AppScaffold(
        currentRoute = currentRouteString,
        onNavigate = { route ->
            when (route) {
                gamesRoute -> {
                    backStack.clear(); backStack.add(ScreenNav.Games)
                }

                profileRoute -> {
                    backStack.clear(); backStack.add(ScreenNav.Profile)
                }
            }
        },
        topBar = {
            if (currentRoute == ScreenNav.Games) {
                GameTopBar(
                    currentSortType = state.sortType,
                    onSortById = { viewModel.setSortById() },
                    onSortByAlpha = { viewModel.setSortByName() },
                    onFilter = { showFilterDialog = true }
                )
            }
        }
    ) { innerPadding ->
        AppNavigation(paddingValues = innerPadding, backStack = backStack, viewModel = viewModel)
        if (showFilterDialog) {
            if (showFilterDialog) {
                FilterDialog(
                    onDismiss = { showFilterDialog = false },
                    onPlatformSelected = { platformKey ->
                        viewModel.setFilter(platformKey)
                    },
                    currentSelection = state.selectedPlatform
                )
            }
        }
    }
}
