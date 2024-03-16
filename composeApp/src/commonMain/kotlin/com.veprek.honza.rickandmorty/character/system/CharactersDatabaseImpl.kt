package com.veprek.honza.rickandmorty.character.system

import com.veprek.honza.rickandmorty.character.data.CharacterDatabase
import comveprekhonzarickandmorty.character.data.Character
import comveprekhonzarickandmorty.character.data.CharacterQueries

class CharactersDatabaseImpl(
    private val characterQueries: CharacterQueries,
) : CharacterDatabase {
    override fun getFavouriteCharacters(): List<Character> = characterQueries.selectAllCharacters().executeAsList()

    override fun addCharacterToFavourites(character: Character) {
        characterQueries.insertCharacter(id = character.id, name = character.name, status = character.status, imageUrl = character.imageUrl)
    }

    override fun removeCharacterFromFavourites(character: Character) = characterQueries.removeCharacterById(character.id)
}
