package com.veprek.honza.rickandmorty.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CharacterListItem(
    character: Character,
    onCharacterClicked: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .clickable {
                onCharacterClicked(character.id)
            },
        elevation = 12.dp,
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
        ) {
            Image(
                painter = painterResource(character.imageRes),
                contentDescription = "Character avatar",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(64.dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.h6,
                    )
                }
                Text(text = character.status)
            }
        }
    }
}
