package com.example.juegosfavoritos_practica5.ui.game.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juegosfavoritos_practica5.data.domain.model.GameBo
import com.example.juegosfavoritos_practica5.data.domain.model.StudioBo
import com.example.juegosfavoritos_practica5.data.domain.repository.GameRepository
import com.example.juegosfavoritos_practica5.data.local.PreferenceManager
import com.example.juegosfavoritos_practica5.ui.game.state.GameUiState
import com.example.juegosfavoritos_practica5.ui.game.state.SortType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private val DEFAULT_ID = 0
private val DEFAULT_PEGI = 3

class GameViewModel(
    private val preferenceManager: PreferenceManager,
    private val gameRepository: GameRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    private var lastDeletedGame: GameBo? = null

    init {
        viewModelScope.launch {
            gameRepository.getAllGames().collect { gamesList ->
                _uiState.update { it.copy(games = gamesList) }
            }
        }

        viewModelScope.launch {
            preferenceManager.platformFilter.collect { platform ->
                _uiState.update { it.copy(selectedPlatform = platform) }
            }
        }

        viewModelScope.launch {
            preferenceManager.sortOrder.collect { sortType ->
                _uiState.update { it.copy(sortType = sortType) }
            }
        }
    }

    fun setSortById() {
        viewModelScope.launch {
            preferenceManager.saveSortOrder(SortType.ID)
        }
    }

    fun setSortByName() {
        viewModelScope.launch {
            preferenceManager.saveSortOrder(SortType.NAME)
        }
    }

    fun setFilter(platform: String?) {
        viewModelScope.launch {
            preferenceManager.savePlatformFilter(platform)
        }
    }

    fun deleteGame(game: GameBo) {
        lastDeletedGame = game
        viewModelScope.launch {
            gameRepository.deleteGame(game)
        }
    }

    fun undoDelete() {
        lastDeletedGame?.let { game ->
            viewModelScope.launch {
                gameRepository.insertGame(game)
                lastDeletedGame = null
            }
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

        viewModelScope.launch {
            gameRepository.insertGame(newGame)
        }
    }
}