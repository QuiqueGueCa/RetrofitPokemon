package com.example.retrofitpokemon.data.domain.usecase

import com.example.retrofitpokemon.data.domain.model.pokemon_species.PokemonSpeciesModel
import com.example.retrofitpokemon.data.domain.repository.DataProvider
import kotlinx.coroutines.flow.Flow

class GetPokemonSpeciesUseCase(private val dataProvider: DataProvider) {

    operator fun invoke(url: String): Flow<PokemonSpeciesModel> {
        return dataProvider.getPokemonSpecies(url)
    }
}