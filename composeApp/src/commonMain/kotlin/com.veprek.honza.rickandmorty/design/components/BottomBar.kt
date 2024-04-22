package com.veprek.honza.rickandmorty.design.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.veprek.honza.rickandmorty.navigation.system.Screen
import com.veprek.honza.rickandmorty.navigation.system.routeToScreen
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.map
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import rickandmortymobile.composeapp.generated.resources.Res

//import rickandmortymobile.composeapp.generated.resources.bottom_bar_characters
//import rickandmortymobile.composeapp.generated.resources.bottom_bar_favourites
//import rickandmortymobile.composeapp.generated.resources.ic_all

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navigator: Navigator,
) {
    val currentScreen by navigator.currentEntry.map { it?.route?.route.routeToScreen() }
        .collectAsState(initial = null)
    Napier.d("currentScreen: $currentScreen")
    if (currentScreen?.isRoot == true) {
        BottomAppBar(modifier) {
            BottomBarItem(
                isSelected = currentScreen == Screen.List,
                textRes = Res.string.bottom_bar_characters,
                iconPainter = painterResource(Res.drawable.ic_all),
                onClick = { navigator.navigate(Screen.List.route) },
            )
            BottomBarItem(
                isSelected = currentScreen == Screen.Favourite,
                textRes = Res.string.bottom_bar_favourites,
                iconPainter = rememberVectorPainter(Icons.Default.Favorite),
                onClick = { navigator.navigate(Screen.Favourite.route) },
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RowScope.BottomBarItem(
    isSelected: Boolean,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = Color.Gray,
    textRes: StringResource,
    iconPainter: Painter,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        label = {
            Text(
                text = stringResource(textRes),
            )
        },
        onClick = onClick,
        selected = isSelected,
        icon = {
            Icon(
                painter = iconPainter,
                contentDescription = stringResource(textRes),
            )
        },
    )
}
