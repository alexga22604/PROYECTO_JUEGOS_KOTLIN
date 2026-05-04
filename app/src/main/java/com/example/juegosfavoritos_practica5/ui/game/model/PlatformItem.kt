package com.example.juegosfavoritos_practica5.ui.game.model

import androidx.annotation.StringRes

data class PlatformItem(
    @StringRes val nameRes: Int,
    val iconUrl: String,
    val key: String?
)