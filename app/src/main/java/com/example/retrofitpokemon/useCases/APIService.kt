package com.example.retrofitpokemon.useCases

import com.example.retrofitpokemon.data.GetListPokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<GetListPokemonResponse>
}
