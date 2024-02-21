package com.example.retrofitpokemon.data.domain.repository

import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.EvolutionChainDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon.ListPokemonModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon_species.PokemonSpeciesModel
import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseResponse
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

    override fun getListPokemon(limit: Int, offset: Int): Flow<BaseResponse<ListPokemonModel>> {
        return remoteDataSource.getListPokemon(limit, offset)
    }

    override fun getPokemonDetail(idPokemon: Int): Flow<BaseResponse<PokemonDetailModel>> {
        return remoteDataSource.getPokemonDetail(idPokemon)
    }

    override fun getAbilityDetail(url: String): Flow<BaseResponse<AbilityDetailModel>> {
        return remoteDataSource.getAbilityDetail(url)
    }

    override fun getPokemonSpecies(url: String): Flow<BaseResponse<PokemonSpeciesModel>> {
        return remoteDataSource.getPokemonSpecies(url)
    }

    override fun getEvolutionChainDetail(url: String): Flow<BaseResponse<EvolutionChainDetailModel>> {
        return remoteDataSource.getEvolutionChainDetail(url)
    }
}