package com.veprek.honza.rickandmorty.character.presentation.detail

import com.veprek.honza.rickandmorty.character.domain.GetCharacterByIdUseCase
import com.veprek.honza.rickandmorty.character.model.ResultWrapper
import com.veprek.honza.rickandmorty.character.presentation.detail.state.CharacterDetailState
import com.veprek.honza.rickandmorty.design.model.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class CharacterDetailViewModel(
    private val id: Long,
    private val getCharacterById: GetCharacterByIdUseCase,
) : ViewModel() {
    private val _characterState = MutableStateFlow(CharacterDetailState())
    val characterState = _characterState.asStateFlow()

    init {
        getCharacter()
    }

    private fun getCharacter() {
        viewModelScope.launch {
            val result = getCharacterById(id)
            when (result) {
                is ResultWrapper.Success -> {
                    val character = result.value
                    _characterState.update {
                        it.copy(
                            state = ScreenState.Success,
                            character = character,
                        )
                    }
                }

                is ResultWrapper.Error -> {
                    _characterState.update {
                        it.copy(
                            state = ScreenState.Error,
                        )
                    }
                }
            }
        }
    }
}
