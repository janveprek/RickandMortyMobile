package com.veprek.honza.rickandmorty.character.data

import com.veprek.honza.rickandmorty.character.data.mapper.toEntity
import com.veprek.honza.rickandmorty.character.data.mapper.toModel
import com.veprek.honza.rickandmorty.character.domain.CharacterRepository
import com.veprek.honza.rickandmorty.character.model.CharacterDetail
import com.veprek.honza.rickandmorty.character.model.CharacterModel
import com.veprek.honza.rickandmorty.character.model.ResultWrapper
import com.veprek.honza.rickandmorty.character.model.StatusFilter

class CharacterRepositoryImpl(
    private val charactersApi: CharactersApi,
    private val charactersDatabase: CharacterDatabase,
) : CharacterRepository {
    override suspend fun getAllCharacters(page: Long): ResultWrapper<List<CharacterModel>> {
        return try {
            val result =
                charactersApi.getAllCharacters(page)
            result.result?.let {
                var characters = result.result.map { it.toModel() }
                val favourites = getFavouriteCharacters()
                characters =
                    characters.map {
                        if (favourites.any { fav -> fav.id == it.id }) {
                            it.copy(isFavourite = true)
                        } else it
                    }
                ResultWrapper.Success(characters)
            } ?: run {
                ResultWrapper.Success(emptyList())
            }
        } catch (ex: Exception) {
            ResultWrapper.Error(ex)
        }
    }

    override suspend fun getCharactersByName(
        name: String,
        filter: StatusFilter
    ): ResultWrapper<List<CharacterModel>> {
        return try {
            val result =
                charactersApi.getCharactersByName(name, filter)
            result.result?.let {
                var characters = result.result.map { it.toModel() }
                val favourites = getFavouriteCharacters()
                characters =
                    characters.map {
                        if (favourites.any { fav -> fav.id == it.id }) it.copy(
                            isFavourite = true
                        ) else it
                    }
                ResultWrapper.Success(characters)
            } ?: run {
                ResultWrapper.Success(emptyList())
            }
        } catch (ex: Exception) {
            ResultWrapper.Error(ex)
        }
    }

    override suspend fun getFavouriteCharacters(): List<CharacterModel> =
        charactersDatabase.getFavouriteCharacters().map { it.toModel() }

    override suspend fun getFavouriteCharactersByName(
        name: String,
    ): List<CharacterModel> =
        charactersDatabase.getFavouriteCharacters().map { it.toModel() }

    override suspend fun getFavouriteCharacterByName(
        name: String,
        filter: StatusFilter
    ): CharacterModel = charactersDatabase.getFavouriteCharacterByName(name).toModel()

    override suspend fun addCharacterToFavourites(character: CharacterModel) {
        charactersDatabase.addCharacterToFavourites(character.toEntity())
    }

    override suspend fun removeCharacterFromFavourites(character: CharacterModel) {
        charactersDatabase.removeCharacterFromFavourites(character.toEntity())
    }

    override suspend fun getCharacterById(id: Long): ResultWrapper<CharacterDetail> {
        return try {
            ResultWrapper.Success(charactersApi.getCharacterById(id).toModel())
        } catch (ex: Exception) {
            ResultWrapper.Error(ex)
        }
    }
}
