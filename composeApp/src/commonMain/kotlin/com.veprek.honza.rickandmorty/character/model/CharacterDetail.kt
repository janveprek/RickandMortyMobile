package com.veprek.honza.rickandmorty.character.model

data class CharacterDetail(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val iconUrl: String,
    val isFavourite: Boolean = false,
)
