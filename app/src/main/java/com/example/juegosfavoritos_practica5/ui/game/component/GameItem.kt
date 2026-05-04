package com.example.juegosfavoritos_practica5.ui.game.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.juegosfavoritos_practica5.domain.model.GameBo
import com.example.juegosfavoritos_practica5.ui.theme.DarkTitleColor
import com.example.juegosfavoritos_practica5.ui.theme.GameItemBackground

@Composable
fun GameItem(game: GameBo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = GameItemBackground),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .height(100.dp)
        ) {
            AsyncImage(
                model = game.cover,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DarkTitleColor
                )
                Text(
                    text = game.studio.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = game.launchDate,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DarkTitleColor
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    game.platforms.forEach { platformName ->
                        val iconUrl = when (platformName) {
                            "PlayStation" -> "https://assets.streamlinehq.com/image/private/w_300,h_300,ar_1/f_auto/v1/icons/video-games/playstation-isc3f6x60ta8lfk0hb24t.png/playstation-4ng359rzs0ryrajkol4q5.png?_a=DATAiZAAZAA0"
                            "Xbox" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9Rt_VfAs451lrAtGQHRC6tcMW5PoPB2h5uA&s"
                            "Nintendo" -> "https://i.pinimg.com/236x/63/69/2d/63692dec066bac00bb10806880533901.jpg"
                            else -> ""
                        }
                        AsyncImage(
                            model = iconUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(horizontal = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    val pegiUrl = when (game.pegi) {
                        3 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTq0VFJ5SrtF_e0oMczXOTo6P7mZx2sT2CC9w&s"
                        7 -> "https://www.devuego.es/iconos/pegi_7f.jpg"
                        12 -> "https://www.devuego.es/iconos/pegi_12f.jpg"
                        16 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRv9eyVIIBn0731A7tOYH-R2pLOy7HCkBtjmQ&s"
                        18 -> "https://media.s-bol.com/nlZ5LQ7lBZKY/oKpMAA/867x1200.jpg"
                        else -> ""
                    }
                    AsyncImage(
                        model = pegiUrl,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}