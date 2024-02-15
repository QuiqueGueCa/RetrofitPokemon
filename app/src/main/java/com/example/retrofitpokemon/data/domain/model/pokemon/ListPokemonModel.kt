package com.example.retrofitpokemon.data.domain.model.pokemon

import com.example.retrofitpokemon.data.domain.model.BaseModel
import com.example.retrofitpokemon.data.domain.model.common.PokemonModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListPokemonModel(
    val count: Int = -1,
    val next: String = "",
    val previous: String = "",
    val results: List<PokemonModel> = arrayListOf()
) : BaseModel()