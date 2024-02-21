package com.example.retrofitpokemon.data.domain.repository.remote

import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.EvolutionChainDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon.ListPokemonModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon_species.PokemonSpeciesModel
import com.example.retrofitpokemon.data.domain.repository.DataSource
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ability_detail.AbilityDetailMapper
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.evolution_chain_detail.EvolutionChainDetailMapper
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon.ListPokemonMapper
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon_detail.PokemonDetailMapper
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon_species.PokemonSpeciesMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiCallService: ApiCallService) : DataSource {
    companion object {
        private var INSTANCE: RemoteDataSource? = null

        @Synchronized
        fun getInstance(apiCallService: ApiCallService): RemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSource(apiCallService)
            }
            return INSTANCE!!
        }
    }

    override fun getListPokemon(limit: Int, offset: Int): Flow<BaseResponse<ListPokemonModel>> =
        flow {
            val apiResult = apiCallService.getListPokemon(limit, offset)
            if (apiResult is BaseResponse.Success) {
                emit(BaseResponse.Success(ListPokemonMapper().fromResponse(apiResult.data)))
            } else if (apiResult is BaseResponse.Error) {
                emit(BaseResponse.Error(apiResult.error))
            }
        }

    override fun getPokemonDetail(idPokemon: Int): Flow<BaseResponse<PokemonDetailModel>> = flow {
        val apiResult = apiCallService.getPokemonDetail(idPokemon)
        if (apiResult is BaseResponse.Success) {
            emit(BaseResponse.Success(PokemonDetailMapper().fromResponse(apiResult.data)))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }

    override fun getAbilityDetail(url: String): Flow<BaseResponse<AbilityDetailModel>> = flow {
        val apiResult = apiCallService.getAbilityDetail(url)
        if (apiResult is BaseResponse.Success) {
            emit(BaseResponse.Success(AbilityDetailMapper().fromResponse(apiResult.data)))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }

    override fun getPokemonSpecies(url: String): Flow<BaseResponse<PokemonSpeciesModel>> = flow {
        val apiResult = apiCallService.getPokemonSpecies(url)
        if (apiResult is BaseResponse.Success) {
            emit(BaseResponse.Success(PokemonSpeciesMapper().fromResponse(apiResult.data)))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }

    override fun getEvolutionChainDetail(url: String): Flow<BaseResponse<EvolutionChainDetailModel>> =
        flow {
            val apiResult = apiCallService.getEvolutionChainDetail(url)
            if (apiResult is BaseResponse.Success) {
                emit(BaseResponse.Success(EvolutionChainDetailMapper().fromResponse(apiResult.data)))
            } else if (apiResult is BaseResponse.Error) {
                emit(BaseResponse.Error(apiResult.error))
            }
        }
}