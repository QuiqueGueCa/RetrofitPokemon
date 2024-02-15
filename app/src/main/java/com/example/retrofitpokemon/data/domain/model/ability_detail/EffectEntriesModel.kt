package com.example.retrofitpokemon.data.domain.model.ability_detail

import com.example.retrofitpokemon.data.domain.model.BaseModel
import com.example.retrofitpokemon.data.domain.model.common.LanguageModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class EffectEntriesModel(
    val effect: String = "",
    val shortEffect: String = "",
    val language: LanguageModel = LanguageModel()
) : BaseModel()
