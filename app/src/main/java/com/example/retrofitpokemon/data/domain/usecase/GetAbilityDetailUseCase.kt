package com.example.retrofitpokemon.data.domain.usecase

import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.repository.DataProvider
import kotlinx.coroutines.flow.Flow

class GetAbilityDetailUseCase(private val dataProvider: DataProvider) {

    operator fun invoke(url: String): Flow<AbilityDetailModel> {
        return dataProvider.getAbilityDetail(url)
    }
}