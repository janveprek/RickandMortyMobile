package com.veprek.honza.rickandmorty.app.di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.veprek.honza.rickandmorty.data.AppDatabase
import com.veprek.honza.rickandmorty.data.AppDatabase.Companion.Schema
import org.koin.dsl.module

actual fun platformModule() =
    module {
        single {
            val driver = AndroidSqliteDriver(Schema, get(), "rickandmorty.db")
            AppDatabase(driver)
        }
    }
