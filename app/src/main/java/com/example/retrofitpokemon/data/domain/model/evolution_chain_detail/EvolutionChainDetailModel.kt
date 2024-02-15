package com.example.retrofitpokemon.data.domain.model.evolution_chain_detail

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize


@Parcelize
data class EvolutionChainDetailModel(
    var chain: ChainModel = ChainModel(),
    var id: Int = 0
) : BaseModel()