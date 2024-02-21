package com.example.retrofitpokemon.data.domain.model.pokemon_detail

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class TypeDetailModel(
    val slot: Int = 0,
    val typeModel: TypeModel = TypeModel()
) : BaseModel() {
}