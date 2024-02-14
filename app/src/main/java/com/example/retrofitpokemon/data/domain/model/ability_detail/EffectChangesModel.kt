package com.example.retrofitpokemon.data.domain.model.ability_detail

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class EffectChangesModel(
    val effect: String = "",
    val shortEffect: String = "",
    val language: LanguageModel = LanguageModel()
) : BaseModel()
