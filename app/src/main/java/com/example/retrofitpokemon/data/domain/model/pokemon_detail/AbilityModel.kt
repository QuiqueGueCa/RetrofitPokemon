package com.example.retrofitpokemon.data.domain.model.pokemon_detail

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class AbilityModel(
    val name: String = "",
    val url: String = "",
) : BaseModel()
