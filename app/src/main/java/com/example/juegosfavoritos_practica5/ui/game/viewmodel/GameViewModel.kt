package com.example.juegosfavoritos_practica5.ui.game.viewmodel

import androidx.lifecycle.ViewModel
import com.example.juegosfavoritos_practica5.domain.model.GameBo
import com.example.juegosfavoritos_practica5.domain.model.StudioBo
import com.example.juegosfavoritos_practica5.ui.game.screen.state.GameUiState
import com.example.juegosfavoritos_practica5.ui.game.screen.state.SortType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val DEFAULT_ID = 0
private const val DEFAULT_PEGI = 3

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

    fun addGame(
        name: String,
        studioName: String,
        releaseDate: String,
        coverUrl: String,
        platforms: Set<String>,
        pegi: Int?
    ) {
        val newGame = GameBo(
            id = DEFAULT_ID,
            name = name,
            studio = StudioBo(id = DEFAULT_ID, name = studioName),
            launchDate = releaseDate,
            cover = coverUrl,
            platforms = platforms.toList(),
            pegi = pegi ?: DEFAULT_PEGI
        )

        _uiState.update { currentState ->
            currentState.copy(games = currentState.games + newGame)
        }
    }
}