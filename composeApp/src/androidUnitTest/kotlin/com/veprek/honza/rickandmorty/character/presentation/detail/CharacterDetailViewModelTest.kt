package com.veprek.honza.rickandmorty.character.presentation.detail

import app.cash.turbine.test
import com.veprek.honza.rickandmorty.character.domain.GetCharacterByIdUseCase
import com.veprek.honza.rickandmorty.character.model.CharacterDetail
import com.veprek.honza.rickandmorty.character.model.ResultWrapper
import com.veprek.honza.rickandmorty.design.model.ScreenState
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailViewModelTest {

    private val getCharacterById = mockk<GetCharacterByIdUseCase>()

    private fun createViewModel() = CharacterDetailViewModel(
        getCharacterById = getCharacterById,
        id = 1
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `should get character during init`() = runTest {
        val character = CharacterDetail(
                id = 1,
                name = "Rick",
                status = "alive",
                species = "species",
                type = "type",
                gender = "gender",
                origin = "origin",
                location = "location",
                iconUrl = "icon",
        )
        coEvery { getCharacterById(any()) } returns
                ResultWrapper.Success(character)
        val viewModel = createViewModel()

        viewModel.characterState.test {
            val initialState = awaitItem()
            initialState.state shouldBe ScreenState.Loading
            initialState.character shouldBe null
            val screenState = awaitItem()
            screenState.state shouldBe ScreenState.Success
            screenState.character shouldBe character
        }
    }

    @Test
    fun `getCharacter should show error screen state`() = runTest {
        coEvery { getCharacterById(any()) } returns ResultWrapper.Error(Error())
        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.characterState.test {
            awaitItem().state shouldBe ScreenState.Error
        }
    }
}