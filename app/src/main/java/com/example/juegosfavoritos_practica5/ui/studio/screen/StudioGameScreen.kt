package com.example.juegosfavoritos_practica5.ui.studio.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.juegosfavoritos_practica5.R
import com.example.juegosfavoritos_practica5.ui.game.component.GameItem
import com.example.juegosfavoritos_practica5.ui.game.viewmodel.GameViewModel
import com.example.juegosfavoritos_practica5.ui.theme.DarkTitleColor

@Composable
fun StudioGamesScreen(
    studioName: String,
    viewModel: GameViewModel,
    onBack: () -> Unit,
    bottomBar: @Composable () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    val studioGames = remember(state.games, studioName) {
        state.games.filter { it.studio.name == studioName }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onBack() }
                        .size(20.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = studioName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = DarkTitleColor
                )
            }
        },
        bottomBar = bottomBar
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            if (studioGames.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_games_studio),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(studioGames) { game ->
                        GameItem(
                            game = game,
                            onStudioClick = { }
                        )
                    }
                }
            }
        }
    }
}