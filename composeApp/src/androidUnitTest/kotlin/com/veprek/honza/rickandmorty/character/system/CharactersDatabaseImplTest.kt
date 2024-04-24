package com.veprek.honza.rickandmorty.character.system

import comveprekhonzarickandmorty.character.data.Character
import comveprekhonzarickandmorty.character.data.CharacterQueries
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.junit.Assert.assertEquals
import org.junit.Test

class CharactersDatabaseImplTest {

    @Test
    fun `getFavouriteCharacters calls select all query`() {
        val characterQueries = mockk<CharacterQueries>(relaxed = true)
        val database = CharactersDatabaseImpl(characterQueries)

        val characters = listOf(
            Character(1, "Rick Sanchez", "Alive", "icon"),
            Character(2, "Morty Smith", "Alive", "icon/morty.png")
        )
        every { characterQueries.selectAllCharacters().executeAsList() } returns characters
        val result = database.getFavouriteCharacters()

        assertEquals(characters, result)
    }

    @Test
    fun `getFavouriteCharactersByName calls select query and filters`() {
        val characters = listOf(
            Character(1, "Rick Sanchez", "Alive", "icon"),
            Character(2, "Morty Smith", "Alive", "icon/morty.png")
        )
        val characterQueries = mockk<CharacterQueries> {
            every { selectCharactersByName(any()).executeAsList() } returns characters
        }
        val database = CharactersDatabaseImpl(characterQueries)
        val result = database.getFavouriteCharactersByName("name")

        assertEquals(characters, result)
    }

    @Test
    fun `getFavouriteCharacterByName calls select query`() {
        val characters = listOf(
            Character(1, "Rick Sanchez", "Alive", "icon"),
            Character(2, "Morty Smith", "Alive", "icon/morty.png")
        )
        val expected = characters[0]
        val characterQueries = mockk<CharacterQueries> {
            every { selectCharacterByName(any()).executeAsOne() } returns expected
        }
        val database = CharactersDatabaseImpl(characterQueries)
        val result = database.getFavouriteCharacterByName("name")

        assertEquals(expected, result)
    }

    @Test
    fun `addCharacterToFavourites calls insert query`() {
        val characterQueries = mockk<CharacterQueries> {
            every { insertCharacter(any(), any(), any(), any()) } just runs
        }
        val database = CharactersDatabaseImpl(characterQueries)

        database.addCharacterToFavourites(Character(1, "name", "status", "url"))
        coVerify(exactly = 1) { characterQueries.insertCharacter(any(), any(), any(), any()) }
    }

    @Test
    fun `removeCharacterFromFavourites calls remove query`() {
        val characterQueries = mockk<CharacterQueries> {
            every { removeCharacterById(any()) } just runs
        }
        val database = CharactersDatabaseImpl(characterQueries)

        database.removeCharacterFromFavourites(Character(1, "name", "status", "url"))
        coVerify(exactly = 1) { characterQueries.removeCharacterById(any()) }
    }
}
