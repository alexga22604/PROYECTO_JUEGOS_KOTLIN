package com.example.juegosfavoritos_practica5.ui.game.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.juegosfavoritos_practica5.R

@Composable
fun FilterDialog(
    onDismiss: () -> Unit,
    onPlatformSelected: (String?) -> Unit,
    platforms: List<String>
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.filter_dialog_title)) },
        text = {
            Column {
                TextButton(onClick = { onPlatformSelected(null); onDismiss() }) {
                    Text(stringResource(R.string.platform_all))
                }
                platforms.forEach { platform ->
                    TextButton(onClick = { onPlatformSelected(platform); onDismiss() }) {
                        Text(platform)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.filter_dialog_cancel)) }
        }
    )
}