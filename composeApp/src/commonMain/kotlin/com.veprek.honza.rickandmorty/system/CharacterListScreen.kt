package com.veprek.honza.rickandmorty.system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.veprek.honza.rickandmorty.data.CharactersDataSource
import com.veprek.honza.rickandmorty.design.components.BottomBar
import com.veprek.honza.rickandmorty.design.components.CharacterCard
import com.veprek.honza.rickandmorty.design.theme.Gray
import com.veprek.honza.rickandmorty.design.theme.paddingSmall

@Composable
fun CharacterListScreen() {
    val characters = CharactersDataSource.getAllCharacters()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "Characters")
                        Icon(
                            modifier =
                                Modifier
                                    .padding(start = 8.dp, end = 24.dp)
                                    .clickable {
                                    },
                            painter = rememberVectorPainter(Icons.Default.Search),
                            contentDescription = "Search",
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomBar()
        },
    ) {
        Column(Modifier.fillMaxSize().background(Gray), horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(paddingSmall),
            ) {
                items(characters) { character ->
                    CharacterCard(
                        character = character,
                        onCharacterClicked = {},
                    )
                }
            }
        }
    }
}
