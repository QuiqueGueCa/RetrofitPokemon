package com.example.retrofitpokemon.data.domain.model.evolution_chain_detail

import com.example.retrofitpokemon.data.domain.model.BaseModel
import com.example.retrofitpokemon.data.domain.model.common.SpeciesModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChainModel(
    var pokemonBaseName: ArrayList<String> = arrayListOf(),
    var pokemonFirstEvoNames: ArrayList<String> = arrayListOf(),
    var pokemonSecondEvoNames: ArrayList<String> = arrayListOf(),
    var pokemonThirdEvoNames: ArrayList<String> = arrayListOf(),
    var species: SpeciesModel = SpeciesModel()
) : BaseModel()