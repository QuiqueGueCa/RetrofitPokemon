package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_species

import com.example.retrofitpokemon.data.domain.repository.remote.response.common.LanguageResponse
import com.google.gson.annotations.SerializedName


data class GeneraResponse(

    @SerializedName("genus") var genus: String?,
    @SerializedName("language") var language: LanguageResponse?

)