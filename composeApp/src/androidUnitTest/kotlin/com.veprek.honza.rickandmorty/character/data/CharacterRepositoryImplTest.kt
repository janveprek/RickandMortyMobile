package com.veprek.honza.rickandmorty.character.data

import com.veprek.honza.rickandmorty.character.data.entity.CharacterDetailDto
import com.veprek.honza.rickandmorty.character.data.entity.CharacterDto
import com.veprek.honza.rickandmorty.character.data.entity.LocationDto
import com.veprek.honza.rickandmorty.character.data.entity.OriginDto
import com.veprek.honza.rickandmorty.character.data.entity.PagedResultDto
import com.veprek.honza.rickandmorty.character.data.mapper.toModel
import com.veprek.honza.rickandmorty.character.model.CharacterModel
import com.veprek.honza.rickandmorty.character.model.ResultWrapper
import com.veprek.honza.rickandmorty.character.model.StatusFilter
import comveprekhonzarickandmorty.character.data.Character
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertTrue

class CharacterRepositoryImplTest {
    @Test
    fun `getAllCharacters returns characters`() = runTest {
        val charactersApi = mockk<CharactersApi> {
            coEvery { getAllCharacters(any()) } returns PagedResultDto(
                result = listOf(CharacterDto(id = 1, name = "Rick", status = "alive", image = "")),
            )
        }
        val charactersDatabase = mockk<CharacterDatabase> {
            every { getFavouriteCharacters() } returns emptyList()
        }

        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)

        val result = repository.getAllCharacters(1)

        assertTrue(result is ResultWrapper.Success)
        assertEquals(1, result.value.size)
        assertContains(
            result.value,
            CharacterModel(
                id = 1,
                name = "Rick",
                status = "alive",
                iconUrl = "",
                isFavourite = false
            )
        )
    }

    @Test
    fun `getAllCharacters returns empty list if api error`() = runTest {
        val charactersApi = mockk<CharactersApi> {
            coEvery { getAllCharacters(any()) } returns PagedResultDto()
        }
        val charactersDatabase = mockk<CharacterDatabase> {
            every { getFavouriteCharacters() } returns emptyList()
        }

        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)

        val result = repository.getAllCharacters(1)

        assertTrue(result is ResultWrapper.Success)
        assertEquals(0, result.value.size)
        assertEquals(listOf<CharacterModel>(), result.value)
    }

    @Test
    fun `getCharactersByName returns empty list if api error`() = runTest {
        val charactersApi = mockk<CharactersApi> {
            coEvery { getCharactersByName(any(), any()) } returns PagedResultDto()
        }
        val charactersDatabase = mockk<CharacterDatabase> {
            every { getFavouriteCharacters() } returns emptyList()
        }

        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)

        val result = repository.getCharactersByName("name", StatusFilter.Alive)

        assertTrue(result is ResultWrapper.Success)
        assertEquals(0, result.value.size)
        assertEquals(listOf<CharacterModel>(), result.value)
    }

    @Test
    fun `getAllCharacters returns favorite characters`() = runTest {
        val charactersApi = mockk<CharactersApi> {
            coEvery { getAllCharacters(any()) } returns PagedResultDto(
                result = listOf(
                    CharacterDto(id = 1, name = "Rick", status = "alive", image = ""),
                    CharacterDto(id = 2, name = "Morty", status = "alive", image = ""),
                ),
            )
        }
        val charactersDatabase = mockk<CharacterDatabase> {
            every { getFavouriteCharacters() } returns listOf(
                Character(id = 1, name = "Rick", status = "alive", imageUrl = "")
            )
        }

        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)

        val result = repository.getAllCharacters(1)

        assertTrue(result is ResultWrapper.Success)
        assertEquals(2, result.value.size)
        assertEquals(
            result.value,
            listOf(
                CharacterModel(
                    id = 1,
                    name = "Rick",
                    status = "alive",
                    iconUrl = "",
                    isFavourite = true
                ),
                CharacterModel(
                    id = 2,
                    name = "Morty",
                    status = "alive",
                    iconUrl = "",
                    isFavourite = false
                ),
            )
        )
    }

    @Test
    fun `getCharactersByName returns characters by name`() = runTest {
        val charactersApi = mockk<CharactersApi> {
            coEvery { getCharactersByName(any()) } returns PagedResultDto(
                result = listOf(
                    CharacterDto(id = 1, name = "Rick", status = "alive", image = ""),
                    CharacterDto(id = 2, name = "Morty", status = "alive", image = ""),
                ),
            )
        }
        val charactersDatabase = mockk<CharacterDatabase> {
            every { getFavouriteCharacters() } returns listOf(
                Character(id = 1, name = "Rick", status = "alive", imageUrl = "")
            )
        }

        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)

        val result = repository.getCharactersByName("name")

        assertTrue(result is ResultWrapper.Success)
        assertEquals(2, result.value.size)
        assertEquals(
            result.value,
            listOf(
                CharacterModel(
                    id = 1,
                    name = "Rick",
                    status = "alive",
                    iconUrl = "",
                    isFavourite = true
                ),
                CharacterModel(
                    id = 2,
                    name = "Morty",
                    status = "alive",
                    iconUrl = "",
                    isFavourite = false
                ),
            )
        )
    }

    @Test
    fun `getCharacterById returns character`() = runTest {
        val detail = CharacterDetailDto(
            id = 1,
            name = "Rick",
            status = "alive",
            species = "species",
            type = "type",
            gender = "gender",
            origin = OriginDto("name"),
            location = LocationDto("name"),
            image = "image"
        )

        val charactersApi = mockk<CharactersApi> {
            coEvery { getCharacterById(any()) } returns detail
        }
        val charactersDatabase = mockk<CharacterDatabase>()
        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)
        val result = repository.getCharacterById(1)

        assertTrue(result is ResultWrapper.Success)
        assertEquals(detail.toModel(), result.value)
    }

    @Test
    fun `getFavouriteCharacterByName returns character`() = runTest {
        val character = Character(id = 1, name = "Rick", status = "alive", imageUrl = "")

        val charactersApi = mockk<CharactersApi>()
        val charactersDatabase = mockk<CharacterDatabase> {
            every { getFavouriteCharacterByName(any()) } returns
                    character
        }
        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)
        val result = repository.getFavouriteCharacterByName("name", StatusFilter.Dead)

        assertEquals(character.toModel(), result)
    }

    @Test
    fun `getFavouriteCharactersByName returns characters`() = runTest {
        val character = Character(id = 1, name = "Rick", status = "alive", imageUrl = "")

        val charactersApi = mockk<CharactersApi>()
        val charactersDatabase = mockk<CharacterDatabase> {
            every { getFavouriteCharactersByName(any()) } returns
                    listOf(character)
        }
        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)
        val result = repository.getFavouriteCharactersByName("name")

        assertEquals(listOf(character.toModel()), result)
    }

    @Test
    fun `addCharacterToFavourites calls the right method`() = runTest {
        val character = CharacterModel(id = 1, name = "Rick", status = "alive", iconUrl = "")
        val charactersApi = mockk<CharactersApi>()
        val charactersDatabase = mockk<CharacterDatabase> {
            coEvery { addCharacterToFavourites(any()) } just runs
        }
        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)
        repository.addCharacterToFavourites(character)
        coVerify(exactly = 1) { charactersDatabase.addCharacterToFavourites(any()) }
    }

    @Test
    fun `removeCharacterFromFavourites calls the right method`() = runTest {
        val character = CharacterModel(id = 1, name = "Rick", status = "alive", iconUrl = "")
        val charactersApi = mockk<CharactersApi>()
        val charactersDatabase = mockk<CharacterDatabase> {
            coEvery { removeCharacterFromFavourites(any()) } just runs
        }
        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)
        repository.removeCharacterFromFavourites(character)
        coVerify(exactly = 1) { charactersDatabase.removeCharacterFromFavourites(any()) }
    }

    @Test
    fun `getAllCharacters returns error when exception arises`() = runTest {
        val charactersApi = mockk<CharactersApi> {
            coEvery { getAllCharacters(any()) } throws Exception()
        }
        val charactersDatabase = mockk<CharacterDatabase> {
            every { getFavouriteCharacters() } returns listOf(
                Character(id = 1, name = "Rick", status = "alive", imageUrl = "")
            )
        }

        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)
        val result = repository.getAllCharacters(1)

        assertTrue(result is ResultWrapper.Error)
    }

    @Test
    fun `getCharactersByName returns error when exception arises`() = runTest {
        val charactersApi = mockk<CharactersApi> {
            coEvery { getCharactersByName(any()) } throws Exception()
        }
        val charactersDatabase = mockk<CharacterDatabase> {
            every { getFavouriteCharacters() } returns listOf(
                Character(id = 1, name = "Rick", status = "alive", imageUrl = "")
            )
        }

        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)
        val result = repository.getCharactersByName("name")

        assertTrue(result is ResultWrapper.Error)
    }

    @Test
    fun `getCharacterById returns error when exception arises`() = runTest {
        val charactersApi = mockk<CharactersApi> {
            coEvery { getCharacterById(any()) } throws Exception()
        }
        val charactersDatabase = mockk<CharacterDatabase>()
        val repository = CharacterRepositoryImpl(charactersApi, charactersDatabase)
        val result = repository.getCharacterById(1)

        assertTrue(result is ResultWrapper.Error)
    }
}
