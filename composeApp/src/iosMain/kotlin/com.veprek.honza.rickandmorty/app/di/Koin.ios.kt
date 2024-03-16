package com.veprek.honza.rickandmorty.app.di

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.veprek.honza.rickandmorty.data.AppDatabase
import org.koin.dsl.module

actual fun platformModule() =
    module {
        single {
            val driver = NativeSqliteDriver(AppDatabase.Schema, "rickandmorty.db")
            AppDatabase(driver)
        }
    }
