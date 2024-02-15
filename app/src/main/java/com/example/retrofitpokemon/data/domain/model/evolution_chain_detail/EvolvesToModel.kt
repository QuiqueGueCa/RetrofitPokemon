package com.example.retrofitpokemon.data.domain.model.evolution_chain_detail

import com.example.retrofitpokemon.data.domain.model.BaseModel
import com.example.retrofitpokemon.data.domain.model.common.SpeciesModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class EvolvesToModel(
    var evolvesTo: ArrayList<EvolvesToModel> = arrayListOf(),
    var species: SpeciesModel = SpeciesModel()
) : BaseModel()