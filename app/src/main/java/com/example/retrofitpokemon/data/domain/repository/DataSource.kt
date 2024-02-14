package com.example.retrofitpokemon.data.domain.repository

import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon.ListPokemonModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getListPokemon(limit: Int, offset: Int): Flow<ListPokemonModel>

    fun getPokemonDetail(idPokemon: Int): Flow<PokemonDetailModel>

    fun getAbilityDetail(url: String): Flow<AbilityDetailModel>
}