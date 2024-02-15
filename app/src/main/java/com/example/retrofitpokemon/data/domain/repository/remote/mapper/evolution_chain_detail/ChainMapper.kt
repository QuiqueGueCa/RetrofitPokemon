package com.example.retrofitpokemon.data.domain.repository.remote.mapper.evolution_chain_detail

import com.example.retrofitpokemon.data.domain.model.common.SpeciesModel
import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.ChainModel
import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.EvolvesToModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.common.SpeciesMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.evolution_chain_detail.ChainResponse

class ChainMapper : ResponseMapper<ChainResponse, ChainModel> {
    override fun fromResponse(response: ChainResponse): ChainModel {

        return ChainModel(
            createEvolvesToList(response),
            createSpeciesModel(response)
        )
    }

    private fun createSpeciesModel(response: ChainResponse): SpeciesModel {
        val speciesMapper = SpeciesMapper()
        var speciesModel = SpeciesModel()

        if (response.species != null) {
            speciesModel = speciesMapper.fromResponse(response.species!!)
        }

        return speciesModel
    }

    private fun createEvolvesToList(response: ChainResponse): ArrayList<EvolvesToModel> {
        val evolvesToList = arrayListOf<EvolvesToModel>()

        if (!response.evolvesTo.isNullOrEmpty()) {
            val evolvesToMapper = EvolvesToMapper()
            response.evolvesTo!!.forEach {
                evolvesToList.add(evolvesToMapper.fromResponse(it))
            }
        }

        return evolvesToList
    }
}