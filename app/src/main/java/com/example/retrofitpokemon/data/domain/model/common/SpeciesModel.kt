package com.example.retrofitpokemon.data.domain.model.common

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpeciesModel(
    val name: String = "",
    val url: String = ""
) : BaseModel()
