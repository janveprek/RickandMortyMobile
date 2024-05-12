package com.veprek.honza.rickandmorty.character.presentation.favorite

import com.veprek.honza.rickandmorty.character.domain.AddCharacterToFavouritesUseCase
import com.veprek.honza.rickandmorty.character.domain.GetFavouriteCharactersUseCase
import com.veprek.honza.rickandmorty.character.domain.RemoveCharacterFromFavouritesUseCase
import com.veprek.honza.rickandmorty.character.model.CharacterModel
import com.veprek.honza.rickandmorty.character.presentation.favorite.state.FavoriteCharactersState
import com.veprek.honza.rickandmorty.design.model.ScreenState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

// TODO: When navigating to Favorite Characters, ViewModels somehow die
// could be resolved by an update of library or by using https://www.jetbrains.com/help/kotlin-multiplatform-dev/whats-new-compose-eap.html#lifecycle-library
class FavoriteCharactersViewModel(
    private val getFavouriteCharacters: GetFavouriteCharactersUseCase,
    private val addCharacterToFavourites: AddCharacterToFavouritesUseCase,
    private val removeCharacterFromFavourites: RemoveCharacterFromFavouritesUseCase,
) : ViewModel() {
    private val _charactersState = MutableStateFlow(FavoriteCharactersState(emptyList()))
    val charactersState = _charactersState.asStateFlow()

    init {
        getCharacters()
    }

    override fun onCleared() {
        Napier.d( "ViewModel killed", tag = TAG)
        super.onCleared()
    }

    private fun getCharacters() {
        _charactersState.update { it.copy(state = ScreenState.Loading) }
        viewModelScope.launch {
            val characters = getFavouriteCharacters()
            updateState(characters)
        }
    }

    internal fun toggleFavourite(character: CharacterModel) {
        val updatedCharacter =
            _charactersState.value.characters.find { it.id == character.id }
                ?.copy(isFavourite = !character.isFavourite)
        updatedCharacter?.let { updated ->
            if (updated.isFavourite) {
                viewModelScope.launch {
                    addCharacterToFavourites(character)
                }
            } else {
                viewModelScope.launch {
                    removeCharacterFromFavourites(character)
                }
            }
            val updatedCharacters =
                _charactersState.value.characters.toMutableList()
                    .apply { this[this.indexOf(character)] = updated }
            _charactersState.update {
                it.copy(characters = updatedCharacters)
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            val favourites = getFavouriteCharacters()
            val characters = if (query.isNotEmpty()) {
                favourites.filter { it.name.lowercase().contains(query.lowercase().trim()) }
            } else {
                favourites
            }
            _charactersState.update { it.copy(query = query) }
            updateState(characters)
        }
    }

    private fun updateState(characters: List<CharacterModel>) {
        _charactersState.update {
            if (characters.isEmpty()) {
                it.copy(
                    state = ScreenState.Empty,
                    characters = characters,
                )
            } else {
                it.copy(
                    state = ScreenState.Success,
                    characters = characters,
                )
            }
        }
    }

    companion object {
        private const val TAG = "FavoriteViewModel"
    }
}

