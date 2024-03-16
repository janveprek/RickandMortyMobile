package com.veprek.honza.rickandmorty.character.presentation.detail.state

import com.veprek.honza.rickandmorty.character.model.CharacterDetail
import com.veprek.honza.rickandmorty.character.presentation.list.state.ScreenState

data class CharacterDetailState(
    val character: CharacterDetail? = null,
    val state: ScreenState = ScreenState.Loading,
)
