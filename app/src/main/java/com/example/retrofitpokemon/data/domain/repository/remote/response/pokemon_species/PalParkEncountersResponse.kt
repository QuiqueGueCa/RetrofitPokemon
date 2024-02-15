package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_species

import com.google.gson.annotations.SerializedName


data class PalParkEncountersResponse(

    @SerializedName("area") var area: AreaResponse?,
    @SerializedName("base_score") var baseScore: Int?,
    @SerializedName("rate") var rate: Int?

)