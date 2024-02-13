package com.example.retrofitpokemon.data.domain.model.pokemon

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpritesModel(
    val sprite: String = ""
) : BaseModel()