package com.example.retrofitpokemon.data.domain.repository.remote

import com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail.AbilityDetailResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon.ListPokemonResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.PokemonDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RemoteApiService {
    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<ListPokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") idPokemon: Int
    ): Response<PokemonDetailResponse>


    //De esta manera sustituya la url
    @GET
    suspend fun getAbilityDetail(
        @Url url: String
    ): Response<AbilityDetailResponse>
}