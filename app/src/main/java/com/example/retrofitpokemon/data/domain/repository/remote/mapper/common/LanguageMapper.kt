package com.example.retrofitpokemon.data.domain.repository.remote.mapper.common

import com.example.retrofitpokemon.data.domain.model.common.LanguageModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.common.LanguageResponse

class LanguageMapper : ResponseMapper<LanguageResponse, LanguageModel> {
    override fun fromResponse(response: LanguageResponse): LanguageModel {
        return LanguageModel(response.name ?: "", response.url ?: "")
    }
}