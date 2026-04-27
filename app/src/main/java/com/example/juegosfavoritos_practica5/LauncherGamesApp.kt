package com.example.juegosfavoritos_practica5

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.juegosfavoritos_practica5.ui.navigation.AppNavigation

@Composable
fun LauncherGamesApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        AppNavigation(paddingValues = innerPadding)
    }
}