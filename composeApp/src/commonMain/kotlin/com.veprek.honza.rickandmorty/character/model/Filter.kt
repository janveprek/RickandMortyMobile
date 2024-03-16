package com.veprek.honza.rickandmorty.character.model

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import rickandmortymobile.composeapp.generated.resources.Res
//import rickandmortymobile.composeapp.generated.resources.status_alive
//import rickandmortymobile.composeapp.generated.resources.status_all
//import rickandmortymobile.composeapp.generated.resources.status_dead
//import rickandmortymobile.composeapp.generated.resources.status_unknown

sealed interface Filter

@OptIn(ExperimentalResourceApi::class)
sealed class StatusFilter(
    val nameResource: StringResource,
    val apiName: String,
): Filter {
    data object All : StatusFilter(Res.string.status_all, "")
    data object Alive : StatusFilter(Res.string.status_alive, "alive")
    data object Dead : StatusFilter(Res.string.status_dead, "dead")
    data object Unknown : StatusFilter(Res.string.status_unknown, "unknown")
}
