package com.example.retrofitpokemon.data.domain.repository.remote.mapper.common

import com.example.retrofitpokemon.data.domain.model.common.PokemonModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.common.PokemonResponse

class PokemonMapper : ResponseMapper<PokemonResponse, PokemonModel> {
    override fun fromResponse(response: PokemonResponse): PokemonModel {
        return PokemonModel(response.name ?: "", response.url ?: "")
    }
}