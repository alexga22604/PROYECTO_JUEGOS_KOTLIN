package com.example.juegosfavoritos_practica5.ui.welcome.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

private const val DELAY_MILIS = 1000L
private const val DEFAULT_REPLAY = 0

class WelcomeViewModel : ViewModel() {
    private val _navigateToGames = MutableSharedFlow<Unit>(replay = DEFAULT_REPLAY)
    val navigateToGames: SharedFlow<Unit> = _navigateToGames.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(DELAY_MILIS)
            _navigateToGames.emit(Unit)
        }
    }
}