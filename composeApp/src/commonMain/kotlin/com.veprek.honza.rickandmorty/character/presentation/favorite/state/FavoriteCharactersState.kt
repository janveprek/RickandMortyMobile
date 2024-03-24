package com.veprek.honza.rickandmorty.character.presentation.favorite.state
import com.veprek.honza.rickandmorty.character.model.CharacterModel
import com.veprek.honza.rickandmorty.design.model.ScreenState

data class FavoriteCharactersState(
    val characters: List<CharacterModel> = emptyList(),
    val state: ScreenState = ScreenState.Loading,
)