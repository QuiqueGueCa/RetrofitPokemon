package com.example.retrofitpokemon.data.domain.repository

import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.EvolutionChainDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon.ListPokemonModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon_species.PokemonSpeciesModel
import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getListPokemon(limit: Int, offset: Int): Flow<BaseResponse<ListPokemonModel>>

    fun getPokemonDetail(idPokemon: Int): Flow<BaseResponse<PokemonDetailModel>>

    fun getAbilityDetail(url: String): Flow<BaseResponse<AbilityDetailModel>>

    fun getPokemonSpecies(url: String): Flow<BaseResponse<PokemonSpeciesModel>>

    fun getEvolutionChainDetail(url: String): Flow<BaseResponse<EvolutionChainDetailModel>>
}