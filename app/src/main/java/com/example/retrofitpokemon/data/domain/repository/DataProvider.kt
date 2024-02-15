package com.example.retrofitpokemon.data.domain.repository

import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon.ListPokemonModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon_species.PokemonSpeciesModel
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

    override fun getAbilityDetail(url: String): Flow<AbilityDetailModel> {
        return remoteDataSource.getAbilityDetail(url)
    }

    override fun getPokemonSpecies(url: String): Flow<PokemonSpeciesModel> {
        return remoteDataSource.getPokemonSpecies(url)
    }
}