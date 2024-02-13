package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon_detail

import com.example.retrofitpokemon.data.domain.model.pokemon_detail.AbilityModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.AbilityResponse

class AbilityMapper : ResponseMapper<AbilityResponse, AbilityModel> {

    override fun fromResponse(response: AbilityResponse): AbilityModel {
        return AbilityModel(
            response.name ?: "",
            response.url ?: ""
        )
    }
}