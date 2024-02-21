package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon_detail

import com.example.retrofitpokemon.data.domain.model.pokemon_detail.TypeModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.TypeResponse

class TypeMapper : ResponseMapper<TypeResponse, TypeModel> {
    override fun fromResponse(response: TypeResponse): TypeModel {
        return TypeModel(response.name ?: "", response.url ?: "")
    }
}