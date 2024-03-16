package com.veprek.honza.rickandmorty.character.domain

import com.veprek.honza.rickandmorty.character.model.CharacterModel

interface GetFavouriteCharactersByNameUseCase {
    suspend operator fun invoke(name: String): List<CharacterModel>
}