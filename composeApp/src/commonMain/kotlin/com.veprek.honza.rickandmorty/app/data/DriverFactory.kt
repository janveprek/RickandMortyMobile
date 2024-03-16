package com.veprek.honza.rickandmorty.app.data

import app.cash.sqldelight.db.SqlDriver
import com.veprek.honza.rickandmorty.data.AppDatabase

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): AppDatabase {
    val driver = driverFactory.createDriver()
    return AppDatabase(driver)
}
