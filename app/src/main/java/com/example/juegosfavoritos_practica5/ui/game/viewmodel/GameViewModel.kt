package com.example.juegosfavoritos_practica5.ui.game.viewmodel

import androidx.lifecycle.ViewModel
import com.example.juegosfavoritos_practica5.domain.model.GameBo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    fun setSortById() {
        _uiState.update { it.copy(sortType = SortType.ID) }
    }

    fun setSortByName() {
        _uiState.update { it.copy(sortType = SortType.NAME) }
    }

    fun setFilter(platform: String?) {
        _uiState.update { it.copy(selectedPlatform = platform) }
    }

    private var lastDeletedGame: GameBo? = null
    fun deleteGame(game: GameBo) {
        lastDeletedGame = game
        _uiState.update { it.copy(games = it.games - game) }
    }

    fun undoDelete() {
        lastDeletedGame?.let { game ->
            _uiState.update { it.copy(games = it.games + game) }
            lastDeletedGame = null
        }
    }
}
