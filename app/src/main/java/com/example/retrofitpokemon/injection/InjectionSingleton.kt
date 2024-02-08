package com.example.retrofitpokemon.injection

import com.example.retrofitpokemon.data.domain.repository.DataProvider
import com.example.retrofitpokemon.data.domain.repository.remote.RemoteApiService
import com.example.retrofitpokemon.data.domain.repository.remote.RemoteDataSource
import com.example.retrofitpokemon.data.domain.repository.remote.RetrofitClient

class InjectionSingleton {
    companion object {
        private fun provideApiServices(): RemoteApiService {
            return RetrofitClient.getInstance().getApiServices()
        }

        fun provideDataSource(): DataProvider {
            return DataProvider.getInstance(RemoteDataSource.getInstance(provideApiServices()))
        }
    }
}