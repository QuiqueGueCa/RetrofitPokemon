package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_species

import com.example.retrofitpokemon.data.domain.repository.remote.response.common.PokemonResponse
import com.google.gson.annotations.SerializedName


data class VarietiesResponse(

    @SerializedName("is_default") var isDefault: Boolean?,
    @SerializedName("pokemon") var pokemon: PokemonResponse?

)