package com.example.retrofitpokemon.data.domain.model.ability_detail

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class AbilityDetailModel(
    val id: Int = -1,
    val name: String = "",
    val effectEntriesModel: EffectEntriesModel = EffectEntriesModel()
) : BaseModel()