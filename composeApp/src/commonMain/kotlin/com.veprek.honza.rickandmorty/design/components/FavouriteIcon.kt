package com.veprek.honza.rickandmorty.design.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.veprek.honza.rickandmorty.design.theme.iconSizeSmall
import com.veprek.honza.rickandmorty.design.theme.paddingExtraSmall
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import rickandmortymobile.composeapp.generated.resources.Res
//import rickandmortymobile.composeapp.generated.resources.ic_favourite
//import rickandmortymobile.composeapp.generated.resources.ic_not_favourite

@OptIn(ExperimentalResourceApi::class)
@Composable
fun FavouriteIcon(
    modifier: Modifier = Modifier,
    isFavourite: Boolean,
    onClick: () -> Unit,
) {
    Icon(
        modifier = modifier.size(iconSizeSmall).clickable{ onClick() }.clip(CircleShape).padding(paddingExtraSmall),
        painter =
            if (isFavourite) {
                painterResource(Res.drawable.ic_favourite)
            } else {
                painterResource(Res.drawable.ic_not_favourite)
            },
        tint = MaterialTheme.colorScheme.primary,
        contentDescription = "Add/Remove from favourites",
    )
}
