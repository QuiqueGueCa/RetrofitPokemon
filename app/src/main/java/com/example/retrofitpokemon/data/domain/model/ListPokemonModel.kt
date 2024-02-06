package com.example.retrofitpokemon.data.domain.model

import com.example.retrofitpokemon.data.domain.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListPokemonModel(
    val count: Int = -1,
    val next: String = "",
    val previous: String = "",
    val results: List<PokemonModel> = arrayListOf()
) : BaseModel()