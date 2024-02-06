package com.example.retrofitpokemon.data.domain.repository

import com.example.retrofitpokemon.data.domain.model.pokemon.ListPokemonModel
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getListPokemon(limit: Int, offset: Int): Flow<ListPokemonModel>
}