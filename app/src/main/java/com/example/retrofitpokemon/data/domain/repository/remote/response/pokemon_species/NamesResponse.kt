package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_species

import com.example.retrofitpokemon.data.domain.repository.remote.response.common.LanguageResponse
import com.google.gson.annotations.SerializedName


data class NamesResponse(

    @SerializedName("language") var language: LanguageResponse?,
    @SerializedName("name") var name: String?

)