package com.veprek.honza.rickandmorty.design.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.veprek.honza.rickandmorty.design.theme.RickAndMortyTheme
import com.veprek.honza.rickandmorty.design.theme.cornerRadiusMedium
import com.veprek.honza.rickandmorty.design.theme.cornerRadiusSmall
import com.veprek.honza.rickandmorty.design.theme.elevation
import com.veprek.honza.rickandmorty.design.theme.iconSizeMedium
import com.veprek.honza.rickandmorty.design.theme.paddingSmall
import com.veprek.honza.rickandmorty.model.Character
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun CharacterCard(
    character: Character,
    onCharacterClicked: (Int) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .padding(all = paddingSmall)
                .clip(RoundedCornerShape(cornerRadiusMedium))
                .clickable {
                    onCharacterClicked(character.id)
                },
        elevation = elevation,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(all = paddingSmall),
        ) {
            KamelImage(
                resource = asyncPainterResource(character.iconUrl),
                contentDescription = "Character avatar",
                modifier =
                    Modifier
                        .clip(RoundedCornerShape(cornerRadiusSmall))
                        .size(iconSizeMedium),
            )
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(all = paddingSmall),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = character.name,
                        style = RickAndMortyTheme.typography.titleMedium,
                    )
                }
                Text(
                    text = character.status,
                    style = RickAndMortyTheme.typography.bodyMedium,
                )
            }
        }
    }
}
