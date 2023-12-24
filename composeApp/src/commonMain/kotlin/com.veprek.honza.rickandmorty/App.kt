package com.veprek.honza.rickandmorty

import androidx.compose.runtime.Composable
import com.veprek.honza.rickandmorty.design.theme.RickAndMortyTheme
import com.veprek.honza.rickandmorty.system.CharacterListScreen

@Composable
fun App() {
    RickAndMortyTheme {
        CharacterListScreen()
    }
}
