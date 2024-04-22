package com.veprek.honza.rickandmorty.character.data

import comveprekhonzarickandmorty.character.data.Character

interface CharacterDatabase {
    fun getFavouriteCharacters(): List<Character>

    fun getFavouriteCharactersByName(name: String): List<Character>

    fun getFavouriteCharacterByName(name: String): Character

    fun addCharacterToFavourites(character: Character)

    fun removeCharacterFromFavourites(character: Character)
}
