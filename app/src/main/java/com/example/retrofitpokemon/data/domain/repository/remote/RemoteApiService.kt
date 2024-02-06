package com.example.retrofitpokemon.data.domain.repository.remote

import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon.ListPokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {
    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<ListPokemonResponse>
}