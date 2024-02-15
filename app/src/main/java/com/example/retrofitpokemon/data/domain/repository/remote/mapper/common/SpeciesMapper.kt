package com.example.retrofitpokemon.data.domain.repository.remote.mapper.common

import com.example.retrofitpokemon.data.domain.model.common.SpeciesModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.common.SpeciesResponse

class SpeciesMapper : ResponseMapper<SpeciesResponse, SpeciesModel> {
    override fun fromResponse(response: SpeciesResponse): SpeciesModel {
        return SpeciesModel(
            response.name ?: "",
            response.url ?: ""
        )
    }
}