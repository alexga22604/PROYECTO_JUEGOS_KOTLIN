package com.example.juegosfavoritos_practica5.ui.game.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.juegosfavoritos_practica5.R
import com.example.juegosfavoritos_practica5.data.domain.repository.GameRepository
import com.example.juegosfavoritos_practica5.ui.game.component.CustomInputField
import com.example.juegosfavoritos_practica5.ui.game.component.PegiDialog
import com.example.juegosfavoritos_practica5.ui.game.component.PlatformIconItem
import com.example.juegosfavoritos_practica5.ui.game.viewmodel.GameViewModel
import com.example.juegosfavoritos_practica5.ui.theme.BottomBarBackground
import com.example.juegosfavoritos_practica5.ui.theme.DarkTitleColor
import com.example.juegosfavoritos_practica5.ui.theme.SaveButtonColor

private const val EMPTY_STRING = ""
private const val DEFAULT_PEGI_AGE = 3

@Composable
fun AddGameScreen(viewModel: GameViewModel, onBack: () -> Unit) {
    var name by remember { mutableStateOf(EMPTY_STRING) }
    var studio by remember { mutableStateOf(EMPTY_STRING) }
    var releaseDate by remember { mutableStateOf(EMPTY_STRING) }
    var coverUrl by remember { mutableStateOf(EMPTY_STRING) }
    var selectedPlatforms by remember { mutableStateOf(setOf<String>()) }
    var selectedPegi by remember { mutableStateOf(DEFAULT_PEGI_AGE) }
    var selectedPegiUrl by remember { mutableStateOf(GameRepository.getPegiIcon(DEFAULT_PEGI_AGE)) }
    var showPegiDialog by remember { mutableStateOf(false) }
    val platformItems = remember { GameRepository.getPlatformFilterList().filter { it.key != null } }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onBack() }
                        .size(20.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.add_game_title),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = DarkTitleColor
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BottomBarBackground)
                    .padding(24.dp)
            ) {
                Button(
                    onClick = {
                        if (name.isNotBlank()) {
                            viewModel.addGame(name, studio, releaseDate, coverUrl, selectedPlatforms, selectedPegi)
                            onBack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SaveButtonColor),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(stringResource(R.string.save_button), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            CustomInputField(stringResource(R.string.label_name), name, { name = it })
            CustomInputField(stringResource(R.string.label_studio), studio, { studio = it })
            CustomInputField(stringResource(R.string.label_release_date), releaseDate, { releaseDate = it }, isYearType = true)
            CustomInputField(stringResource(R.string.label_cover_url), coverUrl, { coverUrl = it })

            Spacer(modifier = Modifier.height(16.dp))

            if (coverUrl.isNotBlank()) {
                Card(
                    modifier = Modifier.size(120.dp).padding(top = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    AsyncImage(
                        model = coverUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.label_platform), color = Color.Gray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        platformItems.forEach { platform ->
                            val key = platform.key ?: EMPTY_STRING
                            PlatformIconItem(
                                url = platform.iconUrl,
                                isSelected = selectedPlatforms.contains(key),
                                onClick = {
                                    selectedPlatforms = if (selectedPlatforms.contains(key)) selectedPlatforms - key else selectedPlatforms + key
                                }
                            )
                        }
                    }
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(R.string.label_pegi), color = Color.Gray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.size(50.dp).clickable { showPegiDialog = true }, contentAlignment = Alignment.Center) {
                        AsyncImage(model = selectedPegiUrl, contentDescription = null, modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    }

    if (showPegiDialog) {
        PegiDialog(onDismiss = { showPegiDialog = false }, onPegiSelected = { age, url ->
            selectedPegi = age
            selectedPegiUrl = url
        })
    }
}