package com.example.retrofitpokemon.data.domain.repository.remote.mapper.ability_detail

import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.ability_detail.EffectChangesModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail.AbilityDetailResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail.EffectChangesResponse

class AbilityDetailMapper : ResponseMapper<AbilityDetailResponse, AbilityDetailModel> {

    override fun fromResponse(response: AbilityDetailResponse): AbilityDetailModel {
        val effectsList: MutableList<EffectChangesModel> = mutableListOf()
        val effectChangesMapper = EffectChangesMapper()

        if (!response.effectChanges.isNullOrEmpty()) {
            for (effect: EffectChangesResponse in response.effectChanges) {
                effectsList.add(effectChangesMapper.fromResponse(effect))
            }
        }


        return AbilityDetailModel(
            response.id ?: -1,
            response.name ?: "",
            effectsList.filter { it.language.name == "en" }[0]
        )
    }
}