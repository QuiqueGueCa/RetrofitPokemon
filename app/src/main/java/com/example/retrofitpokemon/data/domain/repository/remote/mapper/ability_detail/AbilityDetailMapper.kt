package com.example.retrofitpokemon.data.domain.repository.remote.mapper.ability_detail

import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.ability_detail.EffectEntriesModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail.AbilityDetailResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail.EffectEntriesResponse

class AbilityDetailMapper : ResponseMapper<AbilityDetailResponse, AbilityDetailModel> {

    override fun fromResponse(response: AbilityDetailResponse): AbilityDetailModel {
        val effectsList: MutableList<EffectEntriesModel> = mutableListOf()
        val effectEntriesMapper = EffectEntriesMapper()

        if (!response.effectEntries.isNullOrEmpty()) {
            for (effect: EffectEntriesResponse in response.effectEntries) {
                effectsList.add(effectEntriesMapper.fromResponse(effect))
            }
        }

        val effectEntriesModel =
            if (effectsList.any { it.language.name == "en" }) {
                effectsList.filter { it.language.name == "en" }[0]
            } else {
                EffectEntriesModel()
            }

        return AbilityDetailModel(
            response.id ?: -1,
            response.name ?: "",
            effectEntriesModel
        )
    }
}