package com.example.esiea3a.presentation.api

import com.example.esiea3a.presentation.list.Pokemon

data class PokemonDetailResponse (
    val types:List<PokemonSlot>
        )

data class PokemonSlot(
    val Slot: Int,
    val type: PokemonType
)
data class PokemonType(
    val name: String,
    val url: String
)