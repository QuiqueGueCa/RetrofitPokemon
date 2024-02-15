package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_species

import com.example.retrofitpokemon.data.domain.repository.remote.response.common.LanguageResponse
import com.google.gson.annotations.SerializedName


data class FlavorTextEntriesResponse(

    @SerializedName("flavor_text") var flavorText: String?,
    @SerializedName("language") var language: LanguageResponse?,
    @SerializedName("version") var version: VersionResponse?

)