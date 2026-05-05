package com.example.juegosfavoritos_practica5.ui.game.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.juegosfavoritos_practica5.data.domain.repository.GameRepository
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
                        AsyncImage(
                            model = GameRepository.getPlatformIcon(platformName),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(horizontal = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    AsyncImage(
                        model = GameRepository.getPegiIcon(game.pegi),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}