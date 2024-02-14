package com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail

import com.google.gson.annotations.SerializedName

data class LanguageResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)