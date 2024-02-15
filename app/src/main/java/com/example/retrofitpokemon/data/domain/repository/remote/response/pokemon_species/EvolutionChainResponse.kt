package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_species

import com.google.gson.annotations.SerializedName


data class EvolutionChainResponse(

    @SerializedName("url") var url: String?

)