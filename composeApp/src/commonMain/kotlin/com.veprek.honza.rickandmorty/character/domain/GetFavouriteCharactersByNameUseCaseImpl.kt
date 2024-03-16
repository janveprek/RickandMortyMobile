package com.veprek.honza.rickandmorty.character.domain

import com.veprek.honza.rickandmorty.character.model.CharacterModel

class GetFavouriteCharactersByNameUseCaseImpl : GetFavouriteCharactersByNameUseCase {
    override suspend fun invoke(name: String): List<CharacterModel> {
        TODO("Not yet implemented")
    }
}