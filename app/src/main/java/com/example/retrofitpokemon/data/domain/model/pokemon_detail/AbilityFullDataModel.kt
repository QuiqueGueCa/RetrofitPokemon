package com.example.retrofitpokemon.data.domain.model.pokemon_detail

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class AbilityFullDataModel(
    val ability: AbilityModel = AbilityModel(),
    val isHidden: Boolean = false,
    val slot: Int = -1,
) : BaseModel()
