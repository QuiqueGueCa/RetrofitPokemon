package com.example.retrofitpokemon.data.domain.repository.remote.mapper.evolution_chain_detail

import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.ChainModel
import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.EvolutionChainDetailModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.evolution_chain_detail.EvolutionChainDetailResponse

class EvolutionChainDetailMapper :
    ResponseMapper<EvolutionChainDetailResponse, EvolutionChainDetailModel> {
    override fun fromResponse(response: EvolutionChainDetailResponse): EvolutionChainDetailModel {
        val chainMapper = ChainMapper()
        var chainModel = ChainModel()

        if (response.chain != null) {
            chainModel = chainMapper.fromResponse(response.chain!!)
        }

        return EvolutionChainDetailModel(
            chainModel,
            response.id ?: -1
        )
    }
}