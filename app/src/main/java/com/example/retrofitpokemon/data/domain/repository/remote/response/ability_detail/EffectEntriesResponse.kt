package com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail

import com.example.retrofitpokemon.data.domain.repository.remote.response.common.LanguageResponse
import com.google.gson.annotations.SerializedName

data class EffectEntriesResponse(
    @SerializedName("effect")
    val effect: String?,
    @SerializedName("language")
    val language: LanguageResponse?,
    @SerializedName("short_effect")
    val shortEffect: String?,
)
