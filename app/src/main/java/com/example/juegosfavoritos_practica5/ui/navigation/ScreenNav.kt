package com.example.juegosfavoritos_practica5.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenNav : NavKey {
    @Serializable
    data object Welcome : ScreenNav()

    @Serializable
    data object Games : ScreenNav()

    @Serializable
    data object Profile : ScreenNav()

    @Serializable
    data object AddGame : ScreenNav()
    data class Studio(val name: String) : ScreenNav()
}