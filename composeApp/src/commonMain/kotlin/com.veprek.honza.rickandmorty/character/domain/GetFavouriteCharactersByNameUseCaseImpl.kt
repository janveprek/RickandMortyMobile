package com.veprek.honza.rickandmorty.character.domain

import com.veprek.honza.rickandmorty.character.model.CharacterModel

class GetFavouriteCharactersByNameUseCaseImpl(
    val repository: CharacterRepository
) : GetFavouriteCharactersByNameUseCase {
    override suspend fun invoke(name: String): List<CharacterModel> {
        return repository.getFavouriteCharactersByName(name)
    }
}