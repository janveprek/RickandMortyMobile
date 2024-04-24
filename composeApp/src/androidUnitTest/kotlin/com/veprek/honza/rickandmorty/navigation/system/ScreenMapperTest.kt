package com.veprek.honza.rickandmorty.navigation.system

import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


class ScreenMapperTest {

    @Test
    fun `should map nullable string to screen`() = runTest {
        assertEquals(Screen.List.route.routeToScreen(), Screen.List)
        assertEquals(Screen.Favourite.route.routeToScreen(), Screen.Favourite)
        assertEquals(Screen.Detail.route.routeToScreen(), Screen.Detail)
        assertEquals(Screen.Search.route.routeToScreen(), Screen.Search)

        "".routeToScreen() shouldBe null
        "test".routeToScreen() shouldBe null
    }

    @Test
    fun `detail screen should return its id in route`() = runTest {
        Screen.Detail.createRoute(0) shouldBe "/character/0"
        Screen.Detail.createRoute(2) shouldBe "/character/2"
    }
}