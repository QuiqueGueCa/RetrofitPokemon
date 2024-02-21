package com.example.retrofitpokemon.data.domain.usecase

import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.EvolutionChainDetailModel
import com.example.retrofitpokemon.data.domain.repository.DataProvider
import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow

class GetEvolutionChainDetailUseCase(private val dataProvider: DataProvider) {

    operator fun invoke(url: String): Flow<BaseResponse<EvolutionChainDetailModel>> {
        return dataProvider.getEvolutionChainDetail(url)
    }
}