package com.example.juegosfavoritos_practica5.ui.game.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.juegosfavoritos_practica5.data.local.PreferenceManager

class GameViewModelFactory(
    private val preferenceManager: PreferenceManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(preferenceManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}