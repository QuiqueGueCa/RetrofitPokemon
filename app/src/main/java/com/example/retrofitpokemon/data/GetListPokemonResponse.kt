package com.example.retrofitpokemon.data

import com.example.retrofitpokemon.data.domain.model.PokemonModel
import com.google.gson.annotations.SerializedName

class GetListPokemonResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: Any,
    @SerializedName("results") val pokemons: List<PokemonModel>
)