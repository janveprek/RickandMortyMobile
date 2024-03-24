package com.veprek.honza.rickandmorty.character.presentation.list.state

import com.veprek.honza.rickandmorty.character.model.CharacterModel
import com.veprek.honza.rickandmorty.character.model.StatusFilter
import com.veprek.honza.rickandmorty.design.model.ScreenState

data class CharacterListState(
    val characters: List<CharacterModel> = emptyList(),
    val query: String = "",
    val openBottomSheet: Boolean = false,
    val appliedFilter: StatusFilter = StatusFilter.All,
    val state: ScreenState = ScreenState.Loading,
)