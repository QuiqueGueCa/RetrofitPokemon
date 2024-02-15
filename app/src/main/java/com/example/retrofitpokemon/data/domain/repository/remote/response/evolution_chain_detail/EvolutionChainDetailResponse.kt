package com.example.retrofitpokemon.data.domain.repository.remote.response.evolution_chain_detail

import com.google.gson.annotations.SerializedName


data class EvolutionChainDetailResponse(

    @SerializedName("baby_trigger_item") var babyTriggerItem: Any?,
    @SerializedName("chain") var chain: ChainResponse?,
    @SerializedName("id") var id: Int?

)