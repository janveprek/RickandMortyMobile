package com.veprek.honza.rickandmorty.design.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.veprek.honza.rickandmorty.design.theme.Blue
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomBar() {
    BottomNavigation {
        BottomNavigationItem(
            label = {
                Text(
                    text = "Characters",
                    color = Blue,
                )
            },
            onClick = {},
            selected = true,
            icon = {
                Icon(
                    painter = painterResource("ic_all.xml"),
                    tint = Blue,
                    contentDescription = "Favourite navigation icon",
                )
            },
        )
        BottomNavigationItem(
            label = {
                Text(
                    text = "Favorites",
                )
            },
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    painter = rememberVectorPainter(Icons.Default.Favorite),
                    contentDescription = "Favourite navigation icon",
                )
            },
        )
    }
}
