package com.example.retrofitpokemon

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Url url: String): Response<PokemonResponse>
}
