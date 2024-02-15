package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon_species

import com.example.retrofitpokemon.data.domain.model.pokemon_species.EvolutionChainModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_species.EvolutionChainResponse

class EvolutionChainMapper : ResponseMapper<EvolutionChainResponse, EvolutionChainModel> {
    override fun fromResponse(response: EvolutionChainResponse): EvolutionChainModel {
        return EvolutionChainModel(response.url ?: "")
    }
}