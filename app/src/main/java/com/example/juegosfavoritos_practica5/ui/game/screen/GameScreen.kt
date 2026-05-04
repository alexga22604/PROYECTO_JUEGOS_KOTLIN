package com.example.juegosfavoritos_practica5.ui.game.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.juegosfavoritos_practica5.R
import com.example.juegosfavoritos_practica5.domain.model.GameBo
import com.example.juegosfavoritos_practica5.ui.game.component.FilterDialog
import com.example.juegosfavoritos_practica5.ui.game.component.GameItem
import com.example.juegosfavoritos_practica5.ui.game.viewmodel.GameViewModel
import com.example.juegosfavoritos_practica5.ui.theme.BottomBarBackground

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen(viewModel: GameViewModel, onNavigateToAddGame: () -> Unit) {
    val state by viewModel.uiState.collectAsState()
    var showFilterDialog by remember { mutableStateOf(false) }
    var gameToDelete by remember { mutableStateOf<GameBo?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (state.filteredAndSortedGames.isEmpty()) {
            Text(
                text = stringResource(R.string.no_favourites_games),
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.filteredAndSortedGames) { game ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = { },
                                onLongClick = { gameToDelete = game }
                            )
                    ) {
                        GameItem(game = game)
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { onNavigateToAddGame() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            containerColor = BottomBarBackground,
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_floating_btn))
        }
    }

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
