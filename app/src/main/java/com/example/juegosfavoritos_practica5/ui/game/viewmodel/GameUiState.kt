package com.example.juegosfavoritos_practica5.ui.game.viewmodel

import com.example.juegosfavoritos_practica5.domain.model.GameBo

enum class SortType {
    ID, NAME
}

data class GameUiState(
    val games: List<GameBo> = emptyList(),
    val sortType: SortType = SortType.ID,
    val selectedPlatform: String? = null
) {
    val filteredAndSortedGames: List<GameBo>
        get() = games
            .filter { selectedPlatform == null || it.platforms.contains(selectedPlatform) }
            .sortedWith(
                when (sortType) {
                    SortType.ID -> compareBy { it.id }
                    SortType.NAME -> compareBy { it.name }
                }
            )
}