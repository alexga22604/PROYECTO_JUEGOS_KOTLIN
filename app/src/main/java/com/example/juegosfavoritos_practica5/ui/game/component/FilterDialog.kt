package com.example.juegosfavoritos_practica5.ui.game.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.juegosfavoritos_practica5.R
import com.example.juegosfavoritos_practica5.ui.game.model.PlatformItem
import com.example.juegosfavoritos_practica5.ui.theme.BottomBarBackground
import com.example.juegosfavoritos_practica5.ui.theme.DarkTitleColor
import com.example.juegosfavoritos_practica5.ui.theme.SaveButtonColor

private const val PLATFORM_PLAYSTATION = "PlayStation"
private const val PLATFORM_XBOX = "Xbox"
private const val PLATFORM_NINTENDO = "Nintendo"

private const val URL_PLAYSTATION = "https://assets.streamlinehq.com/image/private/w_300,h_300,ar_1/f_auto/v1/icons/video-games/playstation-isc3f6x60ta8lfk0hb24t.png/playstation-4ng359rzs0ryrajkol4q5.png?_a=DATAiZAAZAA0"
private const val URL_XBOX = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9Rt_VfAs451lrAtGQHRC6tcMW5PoPB2h5uA&s"
private const val URL_NINTENDO = "https://i.pinimg.com/236x/63/69/2d/63692dec066bac00bb10806880533901.jpg"
private const val URL_ALL_PLATFORMS = "https://us.123rf.com/450wm/martialred/martialred1710/martialred171000010/88316249-geometric-shapes-vector-icon-for-education-apps-and-websites.jpg"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDialog(
    onDismiss: () -> Unit,
    onPlatformSelected: (String?) -> Unit,
    currentSelection: String?
) {
    val platforms = remember {
        listOf(
            PlatformItem(R.string.platform_playstation, URL_PLAYSTATION, PLATFORM_PLAYSTATION),
            PlatformItem(R.string.platform_xbox, URL_XBOX, PLATFORM_XBOX),
            PlatformItem(R.string.platform_nintendo, URL_NINTENDO, PLATFORM_NINTENDO),
            PlatformItem(R.string.platform_all, URL_ALL_PLATFORMS, null)
        )
    }

    var tempSelection by remember { mutableStateOf(currentSelection) }

    BasicAlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .fillMaxHeight(0.90f)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.filter_dialog_title),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = Color.Gray)
                }
            }

            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                platforms.forEach { platform ->
                    val isSelected = tempSelection == platform.key
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(32.dp))
                            .background(if (isSelected) Color.Gray else Color.Transparent)
                            .clickable { tempSelection = platform.key }
                            .padding(horizontal = 24.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = platform.iconUrl,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.width(24.dp))

                        Text(
                            text = stringResource(platform.nameRes),
                            style = MaterialTheme.typography.titleLarge,
                            color = DarkTitleColor,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Column {
                HorizontalDivider(color = Color.LightGray)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BottomBarBackground)
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            onPlatformSelected(tempSelection)
                            onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = SaveButtonColor),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.filter_dialog_accept),
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}