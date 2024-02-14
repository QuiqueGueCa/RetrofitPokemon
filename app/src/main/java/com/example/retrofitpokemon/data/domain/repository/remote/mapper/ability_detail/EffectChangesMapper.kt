package com.example.retrofitpokemon.data.domain.repository.remote.mapper.ability_detail

import com.example.retrofitpokemon.data.domain.model.ability_detail.EffectChangesModel
import com.example.retrofitpokemon.data.domain.model.ability_detail.LanguageModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail.EffectChangesResponse

class EffectChangesMapper : ResponseMapper<EffectChangesResponse, EffectChangesModel> {

    override fun fromResponse(response: EffectChangesResponse): EffectChangesModel {
        val languageMapper = LanguageMapper()
        var languageModel = LanguageModel()
        if (response.language != null) {
            languageModel = languageMapper.fromResponse(response.language)
        }

        return EffectChangesModel(
            response.effect ?: "",
            response.shortEffect ?: "",
            languageModel
        )
    }
}