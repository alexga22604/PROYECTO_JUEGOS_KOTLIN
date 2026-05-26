package com.example.juegosfavoritos_practica5.ui.game.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.juegosfavoritos_practica5.R
import com.example.juegosfavoritos_practica5.ui.game.state.SortType

@Composable
fun GameTopBar(
    onSortById: () -> Unit,
    onSortByAlpha: () -> Unit,
    onFilter: () -> Unit,
    currentSortType: SortType
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.title_games),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            SelectableIconButton(
                icon = Icons.Default.FormatListNumbered,
                contentDescription = stringResource(id = R.string.content_desc_sort_id),
                onClick = onSortById,
                isSelected = currentSortType == SortType.ID
            )
            SelectableIconButton(
                icon = Icons.Default.SortByAlpha,
                contentDescription = stringResource(id = R.string.content_desc_sort_alpha),
                onClick = onSortByAlpha,
                isSelected = currentSortType == SortType.NAME
            )
            IconButton(onClick = onFilter) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = stringResource(id = R.string.content_desc_filter),
                    tint = Color.Black
                )
            }
        }

        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
    }
}

@Composable
fun SelectableIconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    IconButton(onClick = onClick) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = if (isSelected) MaterialTheme.colorScheme.surfaceVariant else Color.Transparent,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = Color.Black
            )
        }
    }
}