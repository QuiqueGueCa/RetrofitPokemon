package com.example.retrofitpokemon.models

import com.google.gson.annotations.SerializedName

class GetListPokemonResponse(
    @SerializedName("results") val pokemons: List<PokemonModel>
)