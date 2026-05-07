package com.example.juegosfavoritos_practica5.ui.game.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.juegosfavoritos_practica5.data.domain.repository.GameRepository
import com.example.juegosfavoritos_practica5.data.local.PreferenceManager

class GameViewModelFactory(
    private val preferenceManager: PreferenceManager,
    private val gameRepository: GameRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(preferenceManager, gameRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}