package com.example.juegosfavoritos_practica5.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.juegosfavoritos_practica5.R
import com.example.juegosfavoritos_practica5.ui.theme.BottomBarBackground
import com.example.juegosfavoritos_practica5.ui.theme.BottomBarSelected
import com.example.juegosfavoritos_practica5.ui.theme.BottomBarUnselected

@Composable
fun BottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val gamesRoute = stringResource(R.string.games_route)
    val profileRoute = stringResource(R.string.profile_route)

    NavigationBar(
        containerColor = BottomBarBackground
    ) {
        NavigationBarItem(
            selected = currentRoute == gamesRoute,
            onClick = { onNavigate(gamesRoute) },
            icon = {
                Icon(
                    Icons.Default.List,
                    contentDescription = stringResource(R.string.games_bottombar),
                    modifier = Modifier.size(48.dp)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BottomBarSelected,
                unselectedIconColor = BottomBarUnselected,
                selectedTextColor = BottomBarSelected,
                unselectedTextColor = BottomBarUnselected,
                indicatorColor = BottomBarBackground
            )
        )

        NavigationBarItem(
            selected = currentRoute == profileRoute,
            onClick = { onNavigate(profileRoute) },
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = stringResource(R.string.profile_bottombar),
                    modifier = Modifier.size(48.dp)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = BottomBarSelected,
                unselectedIconColor = BottomBarUnselected,
                selectedTextColor = BottomBarSelected,
                unselectedTextColor = BottomBarUnselected,
                indicatorColor = BottomBarBackground
            )
        )
    }
}