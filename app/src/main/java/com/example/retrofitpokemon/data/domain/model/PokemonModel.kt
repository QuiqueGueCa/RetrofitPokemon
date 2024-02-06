package com.example.retrofitpokemon.data.domain.model

import com.example.retrofitpokemon.data.domain.BaseModel

data class PokemonModel(
    val name: String = "",
    val url: String = ""
) : BaseModel()