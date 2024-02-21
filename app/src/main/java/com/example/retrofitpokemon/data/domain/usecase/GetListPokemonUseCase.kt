package com.example.retrofitpokemon.data.domain.usecase

import com.example.retrofitpokemon.data.domain.model.pokemon.ListPokemonModel
import com.example.retrofitpokemon.data.domain.repository.DataProvider
import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow

class GetListPokemonUseCase(private val dataProvider: DataProvider) {
    operator fun invoke(limit: Int, offset: Int): Flow<BaseResponse<ListPokemonModel>> {
        return dataProvider.getListPokemon(limit, offset)
    }
}