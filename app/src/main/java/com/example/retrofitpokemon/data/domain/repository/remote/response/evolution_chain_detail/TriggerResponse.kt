package com.example.retrofitpokemon.data.domain.repository.remote.response.evolution_chain_detail

import com.google.gson.annotations.SerializedName


data class TriggerResponse(

    @SerializedName("name") var name: String?,
    @SerializedName("url") var url: String?

)