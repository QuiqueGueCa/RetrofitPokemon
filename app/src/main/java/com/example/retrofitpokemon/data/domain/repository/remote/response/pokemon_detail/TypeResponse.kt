package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail

import com.google.gson.annotations.SerializedName

data class TypeResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)
