package com.veprek.honza.rickandmorty.character.data

import comveprekhonzarickandmorty.character.data.Character

interface CharacterDatabase {
    fun getFavouriteCharacters(): List<Character>

    fun addCharacterToFavourites(character: Character)

    fun removeCharacterFromFavourites(character: Character)
}
