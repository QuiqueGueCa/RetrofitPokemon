package com.example.retrofitpokemon.ui.detail_fragment

import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.EvolutionChainDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel

sealed class DetailFragmentUiState {
    object Loading : DetailFragmentUiState()

    data class Success(
        val pokemonDetailModel: PokemonDetailModel,
        val abilities: MutableList<AbilityDetailModel>,
        val evolutionChainDetailModel: EvolutionChainDetailModel
    ) : DetailFragmentUiState()

    data class Error(val msg: String) : DetailFragmentUiState()
}
