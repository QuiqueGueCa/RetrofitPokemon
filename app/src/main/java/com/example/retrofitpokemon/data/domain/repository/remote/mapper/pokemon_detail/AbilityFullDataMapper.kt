package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon_detail

import com.example.retrofitpokemon.data.domain.model.pokemon_detail.AbilityFullDataModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.AbilityModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.AbilityFullDataResponse

class AbilityFullDataMapper : ResponseMapper<AbilityFullDataResponse, AbilityFullDataModel> {

    override fun fromResponse(response: AbilityFullDataResponse): AbilityFullDataModel {
        val abilityMapper = AbilityMapper()
        var abilityModel = AbilityModel()
        if (response.ability != null) {
            abilityModel = abilityMapper.fromResponse(response.ability)
        }


        return AbilityFullDataModel(
            abilityModel,
            response.isHidden ?: false,
            response.slot ?: -1
        )
    }
}