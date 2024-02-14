package com.example.retrofitpokemon.data.domain.repository.remote

import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon.ListPokemonModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.repository.DataSource
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ability_detail.AbilityDetailMapper
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon.ListPokemonMapper
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon_detail.PokemonDetailMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val remoteApiService: RemoteApiService) : DataSource {
    companion object {
        private var INSTANCE: RemoteDataSource? = null

        @Synchronized
        fun getInstance(remoteApiService: RemoteApiService): RemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSource(remoteApiService)
            }
            return INSTANCE!!
        }
    }

    override fun getListPokemon(limit: Int, offset: Int): Flow<ListPokemonModel> = flow {
        emit(
            ListPokemonMapper().fromResponse(
                remoteApiService.getListPokemon(limit, offset).body()!!
            )
        )
    }

    override fun getPokemonDetail(idPokemon: Int): Flow<PokemonDetailModel> = flow {
        emit(
            PokemonDetailMapper().fromResponse(
                remoteApiService.getPokemonDetail(idPokemon).body()!!
            )
        )
    }

    override fun getAbilitiesContent(url: String): Flow<AbilityDetailModel> = flow {
        emit(
            AbilityDetailMapper().fromResponse(
                remoteApiService.getAbilitiesContent(url).body()!!
            )
        )
    }
}