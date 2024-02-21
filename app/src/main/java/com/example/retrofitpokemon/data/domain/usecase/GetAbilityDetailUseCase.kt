package com.example.retrofitpokemon.data.domain.usecase

import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.repository.DataProvider
import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow

class GetAbilityDetailUseCase(private val dataProvider: DataProvider) {

    operator fun invoke(url: String): Flow<BaseResponse<AbilityDetailModel>> {
        return dataProvider.getAbilityDetail(url)
    }
}