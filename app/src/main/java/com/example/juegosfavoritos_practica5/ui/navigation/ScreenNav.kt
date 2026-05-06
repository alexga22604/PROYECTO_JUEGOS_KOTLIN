package com.example.juegosfavoritos_practica5.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenNav : NavKey {
    @Serializable
    object Welcome : ScreenNav()

    @Serializable
    object Games : ScreenNav()

    @Serializable
    object Profile : ScreenNav()
}