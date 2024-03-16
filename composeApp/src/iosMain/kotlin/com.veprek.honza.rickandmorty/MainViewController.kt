package com.veprek.honza.rickandmorty

import androidx.compose.ui.window.ComposeUIViewController
import com.veprek.honza.rickandmorty.app.di.initKoin

fun MainViewController() =
    ComposeUIViewController {
        initKoin()
        App()
    }
