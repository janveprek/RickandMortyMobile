package com.veprek.honza.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.veprek.honza.rickandmorty.app.di.initKoin
import com.veprek.honza.rickandmorty.app.system.App
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin {
            androidContext(applicationContext)
        }
        setContent {
            App()
        }
    }
}
