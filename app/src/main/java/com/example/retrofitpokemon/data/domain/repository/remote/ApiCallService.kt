package com.example.retrofitpokemon.data.domain.repository.remote

import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseApiCallService
import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail.AbilityDetailResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.evolution_chain_detail.EvolutionChainDetailResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon.ListPokemonResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.PokemonDetailResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_species.PokemonSpeciesResponse

class ApiCallService(private val remoteApiService: RemoteApiService) : BaseApiCallService() {

    suspend fun getListPokemon(limit: Int, offset: Int): BaseResponse<ListPokemonResponse> {
        return apiCall { remoteApiService.getListPokemon(limit, offset) }
    }

    suspend fun getPokemonDetail(idPokemon: Int): BaseResponse<PokemonDetailResponse> {
        return apiCall { remoteApiService.getPokemonDetail(idPokemon) }
    }

    suspend fun getAbilityDetail(url: String): BaseResponse<AbilityDetailResponse> {
        return apiCall { remoteApiService.getAbilityDetail(url) }
    }

    suspend fun getPokemonSpecies(url: String): BaseResponse<PokemonSpeciesResponse> {
        return apiCall { remoteApiService.getPokemonSpecies(url) }
    }

    suspend fun getEvolutionChainDetail(url: String): BaseResponse<EvolutionChainDetailResponse> {
        return apiCall { remoteApiService.getEvolutionChainDetail(url) }
    }
}