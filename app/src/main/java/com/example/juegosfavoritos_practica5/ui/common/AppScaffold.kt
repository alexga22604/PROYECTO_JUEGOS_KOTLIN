package com.example.juegosfavoritos_practica5.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun AppScaffold(
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    topBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = topBar,
        bottomBar = {
            if (!currentRoute.isNullOrEmpty()) {
                BottomBar(
                    currentRoute = currentRoute,
                    onNavigate = onNavigate
                )
            }
        },
        floatingActionButton = floatingActionButton,
        snackbarHost = snackbarHost
    ) { paddingValues ->
        content(paddingValues)
    }
}