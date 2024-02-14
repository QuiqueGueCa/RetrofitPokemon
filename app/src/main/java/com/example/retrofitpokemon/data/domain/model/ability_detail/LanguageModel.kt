package com.example.retrofitpokemon.data.domain.model.ability_detail

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class LanguageModel(
    val name: String = "",
    val url: String = "",
) : BaseModel()