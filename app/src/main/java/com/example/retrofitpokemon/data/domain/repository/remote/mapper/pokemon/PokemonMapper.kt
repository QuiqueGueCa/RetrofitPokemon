package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon

import com.example.retrofitpokemon.data.domain.model.pokemon.PokemonModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon.PokemonResponse

class PokemonMapper : ResponseMapper<PokemonResponse, PokemonModel> {
    override fun fromResponse(response: PokemonResponse): PokemonModel {
        return PokemonModel(response.name ?: "", response.url ?: "")
    }
}