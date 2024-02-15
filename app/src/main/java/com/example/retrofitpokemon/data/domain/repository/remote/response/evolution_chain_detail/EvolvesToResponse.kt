package com.example.retrofitpokemon.data.domain.repository.remote.response.evolution_chain_detail

import com.example.retrofitpokemon.data.domain.repository.remote.response.common.SpeciesResponse
import com.google.gson.annotations.SerializedName


data class EvolvesToResponse(
    @SerializedName("evolution_details") var evolutionDetails: ArrayList<EvolutionDetailsResponse>?,
    @SerializedName("evolves_to") var evolvesTo: ArrayList<EvolvesToResponse>?,
    @SerializedName("is_baby") var isBaby: Boolean?,
    @SerializedName("species") var species: SpeciesResponse?

)