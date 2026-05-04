package com.example.juegosfavoritos_practica5.ui.game.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.juegosfavoritos_practica5.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PegiDialog(
    onDismiss: () -> Unit,
    onPegiSelected: (Int, String) -> Unit
) {
    val pegis = listOf(
        3 to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTq0VFJ5SrtF_e0oMczXOTo6P7mZx2sT2CC9w&s",
        7 to "https://www.devuego.es/iconos/pegi_7f.jpg",
        12 to "https://www.devuego.es/iconos/pegi_12f.jpg",
        16 to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRv9eyVIIBn0731A7tOYH-R2pLOy7HCkBtjmQ&s",
        18 to "https://media.s-bol.com/nlZ5LQ7lBZKY/oKpMAA/867x1200.jpg"
    )

    BasicAlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.pegui_select),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                pegis.forEach { (age, url) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                onPegiSelected(age, url)
                                onDismiss()
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = url,
                            contentDescription = "${stringResource(R.string.label_pegi)} $age",
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = "${stringResource(R.string.label_pegi)} $age",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}