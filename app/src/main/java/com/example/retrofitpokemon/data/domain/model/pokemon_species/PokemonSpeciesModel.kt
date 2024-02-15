package com.example.retrofitpokemon.data.domain.model.pokemon_species

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonSpeciesModel(
    val evolutionChain: EvolutionChainModel = EvolutionChainModel()
) : BaseModel()
