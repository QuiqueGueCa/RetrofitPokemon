package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon

import com.example.retrofitpokemon.data.domain.repository.remote.response.common.PokemonResponse
import com.google.gson.annotations.SerializedName

data class ListPokemonResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<PokemonResponse>?
)