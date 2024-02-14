package com.example.retrofitpokemon.data.domain.repository.remote.mapper.ability_detail

import com.example.retrofitpokemon.data.domain.model.ability_detail.LanguageModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail.LanguageResponse

class LanguageMapper : ResponseMapper<LanguageResponse, LanguageModel> {
    override fun fromResponse(response: LanguageResponse): LanguageModel {
        return LanguageModel(response.name ?: "", response.url ?: "")
    }
}