package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon_detail

import com.example.retrofitpokemon.data.domain.model.pokemon_detail.SpritesModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.SpritesResponse

class SpritesMapper : ResponseMapper<SpritesResponse, SpritesModel> {
    override fun fromResponse(response: SpritesResponse): SpritesModel {
        return SpritesModel(response.frontDefault ?: "")
    }
}