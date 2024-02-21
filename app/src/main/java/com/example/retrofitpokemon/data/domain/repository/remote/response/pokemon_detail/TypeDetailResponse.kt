package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail

import com.google.gson.annotations.SerializedName

data class TypeDetailResponse(
    @SerializedName("slot")
    val slot: Int?,
    @SerializedName("type")
    val type: TypeResponse?
)
