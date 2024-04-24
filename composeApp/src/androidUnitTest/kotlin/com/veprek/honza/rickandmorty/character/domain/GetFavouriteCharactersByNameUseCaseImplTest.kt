package com.veprek.honza.rickandmorty.character.domain

import com.veprek.honza.rickandmorty.character.model.CharacterModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetFavouriteCharactersByNameUseCaseImplTest {
    private val repository = mockk<CharacterRepository>()

    @Test
    fun `should call repository getFavouriteCharactersByName`() = runTest {
        coEvery { repository.getFavouriteCharactersByName(any()) } returns listOf(CharacterModel(1, "name", "status", "url"))

        val useCase = GetFavouriteCharactersByNameUseCaseImpl(repository)
        useCase("name")
        coVerify(exactly = 1) { repository.getFavouriteCharactersByName(any()) }
    }
}
