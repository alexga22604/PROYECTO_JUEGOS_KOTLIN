package com.example.juegosfavoritos_practica5.ui.game.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.juegosfavoritos_practica5.ui.theme.DialogOffWhite

@Composable
fun CustomInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isYearType: Boolean = false
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            value = value,
            onValueChange = { input ->
                if (isYearType) {
                    val digitsOnly = input.filter { it.isDigit() }
                    if (digitsOnly.length <= 4) {
                        onValueChange(digitsOnly)
                    }
                } else {
                    onValueChange(input)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = DialogOffWhite,
                unfocusedContainerColor = DialogOffWhite,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isYearType) KeyboardType.Number else KeyboardType.Text
            )
        )
    }
}

@Composable
fun PlatformIconItem(url: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) DialogOffWhite else Color.Transparent)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) Color.Gray else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        AsyncImage(model = url, contentDescription = null, modifier = Modifier.fillMaxSize())
    }
}