package com.example.retrofitpokemon.data.domain.usecase

import com.example.retrofitpokemon.data.domain.model.pokemon.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.repository.DataProvider
import kotlinx.coroutines.flow.Flow

class GetPokemonDetailUseCase(private val dataProvider: DataProvider) {

    operator fun invoke(idPokemon: Int): Flow<PokemonDetailModel> {
        return dataProvider.getPokemonDetail(idPokemon)
    }
}