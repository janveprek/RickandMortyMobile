package com.veprek.honza.rickandmorty

import androidx.compose.ui.window.ComposeUIViewController
import com.veprek.honza.rickandmorty.app.di.initKoin
import com.veprek.honza.rickandmorty.app.system.App

fun MainViewController() =
    ComposeUIViewController {
        initKoin()
        App()
    }
