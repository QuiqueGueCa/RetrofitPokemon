package com.example.retrofitpokemon.data.domain.model.pokemon_detail

import com.example.retrofitpokemon.data.domain.model.BaseModel
import com.example.retrofitpokemon.data.domain.model.common.SpeciesModel
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
    val spritesModel: SpritesModel = SpritesModel(),
    val abilities: MutableList<AbilityFullDataModel> = mutableListOf(),
    val species: SpeciesModel = SpeciesModel(),
    val types: MutableList<TypeDetailModel> = mutableListOf()
) : BaseModel()