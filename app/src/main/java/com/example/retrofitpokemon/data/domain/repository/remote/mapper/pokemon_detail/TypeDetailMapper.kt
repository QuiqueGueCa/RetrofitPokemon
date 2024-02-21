package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon_detail

import com.example.retrofitpokemon.data.domain.model.pokemon_detail.TypeDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.TypeModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.TypeDetailResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.TypeResponse

class TypeDetailMapper : ResponseMapper<TypeDetailResponse, TypeDetailModel> {
    override fun fromResponse(response: TypeDetailResponse): TypeDetailModel {


        return TypeDetailModel(
            response.slot ?: 0,
            setupTypeModel(response.type)
        )
    }

    private fun setupTypeModel(type: TypeResponse?): TypeModel {
        val typeMapper = TypeMapper()
        var typeModel = TypeModel()
        if (type != null) {
            typeModel = typeMapper.fromResponse(type)
        }
        return typeModel
    }
}