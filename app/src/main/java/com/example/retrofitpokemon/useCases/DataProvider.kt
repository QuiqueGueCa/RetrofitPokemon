package com.example.retrofitpokemon.useCases

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.retrofitpokemon.data.domain.model.PokemonModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataProvider {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val pokemons: LiveData<List<PokemonModel>> = liveData {
        val call = retrofit.create(APIService::class.java).getListPokemon(0, 0)
        val pokemonsResponse = call.body()

        if (call.isSuccessful) {
            this.emit(pokemonsResponse?.pokemons ?: emptyList())
        }
    }
}