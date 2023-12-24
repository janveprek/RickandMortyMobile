package com.veprek.honza.rickandmorty.data

import com.veprek.honza.rickandmorty.model.Character

object CharactersDataSource {
    private val characters =
        listOf(
            Character(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "-",
                gender = "Male",
                origin = "Earth (C - 137)",
                location = "Citadel of Ricks",
                iconUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            ),
            Character(
                id = 2,
                name = "Morty Smith",
                status = "Alive",
                species = "Human",
                type = "-",
                gender = "Male",
                origin = "Unknown",
                location = "Citadel of Ricks",
                iconUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            ),
            Character(
                id = 3,
                name = "Summer Smith",
                status = "Alive",
                species = "Human",
                type = "-",
                gender = "Female",
                origin = "Earth (Replacement Dimension)",
                location = "Earth (Replacement Dimension)",
                iconUrl = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            ),
            Character(
                id = 4,
                name = "Beth Smith",
                status = "Alive",
                species = "Human",
                type = "-",
                gender = "Female",
                origin = "Earth (Replacement Dimension)",
                location = "Earth (Replacement Dimension)",
                iconUrl = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
            ),
            Character(
                id = 5,
                name = "Jerry Smith",
                status = "Alive",
                species = "Human",
                type = "-",
                gender = "Male",
                origin = "Earth (Replacement Dimension)",
                location = "Earth (Replacement Dimension)",
                iconUrl = "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
            ),
            Character(
                id = 6,
                name = "Abadango Cluster Princess",
                status = "Alive",
                species = "Alien",
                type = "-",
                gender = "Female",
                origin = "Abadango",
                location = "Abadango",
                iconUrl = "https://rickandmortyapi.com/api/character/avatar/6.jpeg",
            ),
        )

    fun getAllCharacters(): List<Character> = characters
}
