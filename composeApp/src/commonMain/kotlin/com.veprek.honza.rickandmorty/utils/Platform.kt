package com.veprek.honza.rickandmorty.utils

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform