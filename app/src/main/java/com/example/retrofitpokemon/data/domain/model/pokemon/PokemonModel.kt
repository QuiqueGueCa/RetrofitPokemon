package com.example.retrofitpokemon.data.domain.model.pokemon

import com.example.retrofitpokemon.data.domain.model.BaseModel

data class PokemonModel(
    val name: String = "",
    val url: String = ""
) : BaseModel()