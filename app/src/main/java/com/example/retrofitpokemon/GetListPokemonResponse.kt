package com.example.retrofitpokemon

import com.example.retrofitpokemon.models.PokemonModel
import com.google.gson.annotations.SerializedName

class GetListPokemonResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: Any,
    @SerializedName("results") val pokemons: List<PokemonModel>
)