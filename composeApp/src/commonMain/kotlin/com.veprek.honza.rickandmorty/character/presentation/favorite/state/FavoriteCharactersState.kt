package com.veprek.honza.rickandmorty.character.presentation.favorite.state
import com.veprek.honza.rickandmorty.character.model.CharacterModel
import com.veprek.honza.rickandmorty.character.model.StatusFilter
import com.veprek.honza.rickandmorty.design.model.ScreenState

data class FavoriteCharactersState(
    val characters: List<CharacterModel> = emptyList(),
    val state: ScreenState = ScreenState.Loading,
    val query: String = "",
    val openBottomSheet: Boolean = false,
    val appliedFilter: StatusFilter = StatusFilter.All,
)