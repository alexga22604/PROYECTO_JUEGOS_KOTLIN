package com.example.juegosfavoritos_practica5.ui.welcome.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.juegosfavoritos_practica5.R
import com.example.juegosfavoritos_practica5.ui.welcome.viewmodel.WelcomeViewModel

@Composable
fun WelcomeScreen(viewModel: WelcomeViewModel = viewModel(), onTimeOut: () -> Unit) {
    LaunchedEffect(Unit) {
        viewModel.navigateToGames.collect {
            onTimeOut()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentSize()
        ) {
            Text(
                text = stringResource(R.string.welcome_txt),
                color = Color.Gray,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = Color.Gray,
                strokeWidth = 4.dp
            )
        }
    }
}