package com.example.retrofitpokemon.data.domain.model.pokemon

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailModel(
    val id: Int = -1,
    val name: String = "",
    val weightKg: String = "",
    val weightLb: String = "",
    val weightOz: String = "",
    val heightMeters: String = "",
    val heightFeets: String = "",
    val spritesModel: SpritesModel = SpritesModel()
) : BaseModel()