package com.example.juegosfavoritos_practica5.ui.preference.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.juegosfavoritos_practica5.R
import com.example.juegosfavoritos_practica5.ui.game.state.SortType
import com.example.juegosfavoritos_practica5.ui.game.viewmodel.GameViewModel
import com.example.juegosfavoritos_practica5.ui.preference.component.PreferenceItem

@Composable
fun PreferenceScreen(viewModel: GameViewModel) {
    val state by viewModel.uiState.collectAsState()
    val sortText = when (state.sortType) {
        SortType.NAME -> stringResource(R.string.alphabetical)
        SortType.ID -> stringResource(R.string.by_id)
        else -> stringResource(R.string.default_order)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.preferences_title),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        HorizontalDivider()

        Spacer(modifier = Modifier.height(24.dp))

        PreferenceItem(
            title = stringResource(R.string.preference_filter_platform),
            value = state.selectedPlatform ?: stringResource(R.string.platform_all)
        )

        Spacer(modifier = Modifier.height(24.dp))

        PreferenceItem(
            title = stringResource(R.string.preference_sort_order),
            value = sortText
        )
    }
}