package com.example.retrofitpokemon.data.domain.repository

import com.example.retrofitpokemon.data.domain.model.pokemon.ListPokemonModel
import com.example.retrofitpokemon.data.domain.model.pokemon.PokemonDetailModel
import kotlinx.coroutines.flow.Flow

class DataProvider(private val remoteDataSource: DataSource) : DataSource {
    companion object {
        var INSTANCE: DataProvider? = null

        @Synchronized
        fun getInstance(remoteDataSource: DataSource): DataProvider {
            if (INSTANCE == null) {
                INSTANCE = DataProvider(remoteDataSource)
            }
            return INSTANCE!!
        }
    }

    override fun getListPokemon(limit: Int, offset: Int): Flow<ListPokemonModel> {
        return remoteDataSource.getListPokemon(limit, offset)
    }

    override fun getPokemonDetail(idPokemon: Int): Flow<PokemonDetailModel> {
        return remoteDataSource.getPokemonDetail(idPokemon)
    }
}