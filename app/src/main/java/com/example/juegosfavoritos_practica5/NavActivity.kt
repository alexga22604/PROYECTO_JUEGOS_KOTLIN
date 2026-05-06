package com.example.juegosfavoritos_practica5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.juegosfavoritos_practica5.ui.theme.JuegosFavoritospractica5Theme

class NavActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JuegosFavoritospractica5Theme {
                LauncherGamesApp()
            }
        }
    }
}

