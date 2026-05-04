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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.juegosfavoritos_practica5.R
import com.example.juegosfavoritos_practica5.ui.game.component.CustomInputField
import com.example.juegosfavoritos_practica5.ui.game.component.PegiDialog
import com.example.juegosfavoritos_practica5.ui.game.component.PlatformIconItem
import com.example.juegosfavoritos_practica5.ui.game.model.PlatformItem
import com.example.juegosfavoritos_practica5.ui.game.viewmodel.GameViewModel
import com.example.juegosfavoritos_practica5.ui.theme.BottomBarBackground

private const val EMPTY_STRING = ""

@Composable
fun AddGameScreen(viewModel: GameViewModel, onBack: () -> Unit) {
    var name by remember { mutableStateOf(EMPTY_STRING) }
    var studio by remember { mutableStateOf(EMPTY_STRING) }
    var releaseDate by remember { mutableStateOf(EMPTY_STRING) }
    var coverUrl by remember { mutableStateOf(EMPTY_STRING) }
    var selectedPlatforms by remember { mutableStateOf(setOf<String>()) }

    var selectedPegi by remember { mutableStateOf(3) }
    var selectedPegiUrl by remember {
        mutableStateOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTq0VFJ5SrtF_e0oMczXOTo6P7mZx2sT2CC9w&s")
    }

    var showPegiDialog by remember { mutableStateOf(false) }

    val platformItems = remember {
        listOf(
            PlatformItem(
                R.string.platform_playstation,
                "https://assets.streamlinehq.com/image/private/w_300,h_300,ar_1/f_auto/v1/icons/video-games/playstation-isc3f6x60ta8lfk0hb24t.png/playstation-4ng359rzs0ryrajkol4q5.png?_a=DATAiZAAZAA0",
                "PlayStation"
            ),
            PlatformItem(
                R.string.platform_xbox,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9Rt_VfAs451lrAtGQHRC6tcMW5PoPB2h5uA&s",
                "Xbox"
            ),
            PlatformItem(
                R.string.platform_nintendo,
                "https://i.pinimg.com/236x/63/69/2d/63692dec066bac00bb10806880533901.jpg",
                "Nintendo"
            )
        )
    }

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
                    color = Color(0xFF4A4A58)
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
                            viewModel.addGame(
                                name = name,
                                studioName = studio,
                                releaseDate = releaseDate,
                                coverUrl = coverUrl,
                                platforms = selectedPlatforms,
                                pegi = selectedPegi
                            )
                            onBack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF68A641)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.save_button),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
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
            CustomInputField(
                label = stringResource(R.string.label_name),
                value = name,
                onValueChange = { name = it }
            )
            CustomInputField(
                label = stringResource(R.string.label_studio),
                value = studio,
                onValueChange = { studio = it }
            )
            CustomInputField(
                label = stringResource(R.string.label_release_date),
                value = releaseDate,
                onValueChange = { releaseDate = it }
            )
            CustomInputField(
                label = stringResource(R.string.label_cover_url),
                value = coverUrl,
                onValueChange = { coverUrl = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.label_platform),
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        platformItems.forEach { platform ->
                            val key = platform.key ?: EMPTY_STRING
                            PlatformIconItem(
                                url = platform.iconUrl,
                                isSelected = selectedPlatforms.contains(key),
                                onClick = {
                                    selectedPlatforms = if (selectedPlatforms.contains(key)) {
                                        selectedPlatforms - key
                                    } else {
                                        selectedPlatforms + key
                                    }
                                }
                            )
                        }
                    }
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(R.string.label_pegi),
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { showPegiDialog = true },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = selectedPegiUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }

    if (showPegiDialog) {
        PegiDialog(
            onDismiss = { showPegiDialog = false },
            onPegiSelected = { age, url ->
                selectedPegi = age
                selectedPegiUrl = url
            }
        )
    }
}