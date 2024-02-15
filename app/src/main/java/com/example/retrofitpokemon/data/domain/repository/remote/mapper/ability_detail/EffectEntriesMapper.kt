package com.example.retrofitpokemon.data.domain.repository.remote.mapper.ability_detail

import com.example.retrofitpokemon.data.domain.model.ability_detail.EffectEntriesModel
import com.example.retrofitpokemon.data.domain.model.common.LanguageModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.common.LanguageMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail.EffectEntriesResponse

class EffectEntriesMapper : ResponseMapper<EffectEntriesResponse, EffectEntriesModel> {

    override fun fromResponse(response: EffectEntriesResponse): EffectEntriesModel {
        val languageMapper = LanguageMapper()
        var languageModel = LanguageModel()
        if (response.language != null) {
            languageModel = languageMapper.fromResponse(response.language)
        }

        return EffectEntriesModel(
            response.effect ?: "",
            response.shortEffect ?: "",
            languageModel
        )
    }
}