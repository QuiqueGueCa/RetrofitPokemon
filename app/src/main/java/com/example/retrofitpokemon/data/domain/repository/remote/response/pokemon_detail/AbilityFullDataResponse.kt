package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail

import com.google.gson.annotations.SerializedName

data class AbilityFullDataResponse(
    @SerializedName("ability")
    val ability: AbilityResponse?,
    @SerializedName("is_hidden")
    val isHidden: Boolean?,
    @SerializedName("slot")
    val slot: Int?,
)