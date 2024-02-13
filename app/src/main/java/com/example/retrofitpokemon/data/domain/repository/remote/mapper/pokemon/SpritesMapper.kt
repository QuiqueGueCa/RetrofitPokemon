package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon

import com.example.retrofitpokemon.data.domain.model.pokemon.SpritesModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon.SpritesResponse

class SpritesMapper : ResponseMapper<SpritesResponse, SpritesModel> {
    override fun fromResponse(response: SpritesResponse): SpritesModel {
        return SpritesModel(response.frontDefault ?: "")
    }
}