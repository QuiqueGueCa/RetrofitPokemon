package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon_species

import com.example.retrofitpokemon.data.domain.model.pokemon_species.EvolutionChainModel
import com.example.retrofitpokemon.data.domain.model.pokemon_species.PokemonSpeciesModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_species.PokemonSpeciesResponse

class PokemonSpeciesMapper : ResponseMapper<PokemonSpeciesResponse, PokemonSpeciesModel> {
    override fun fromResponse(response: PokemonSpeciesResponse): PokemonSpeciesModel {
        val evolutionChainMapper = EvolutionChainMapper()
        var evolutionChainModel = EvolutionChainModel()
        if (response.evolutionChain != null) {
            evolutionChainModel = evolutionChainMapper.fromResponse(response.evolutionChain!!)
        }


        return PokemonSpeciesModel(evolutionChainModel)
    }
}