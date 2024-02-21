package com.example.retrofitpokemon.data.domain.usecase

import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.repository.DataProvider
import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow

class GetPokemonDetailUseCase(private val dataProvider: DataProvider) {

    operator fun invoke(idPokemon: Int): Flow<BaseResponse<PokemonDetailModel>> {
        return dataProvider.getPokemonDetail(idPokemon)
    }
}