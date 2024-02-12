package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon

import com.example.retrofitpokemon.data.domain.model.pokemon.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon.PokemonDetailResponse

class PokemonDetailMapper : ResponseMapper<PokemonDetailResponse, PokemonDetailModel> {

    override fun fromResponse(response: PokemonDetailResponse): PokemonDetailModel {
        return PokemonDetailModel(
            response.id ?: -1,
            response.name ?: "",
            convertWeightToKg(response.weight) ?: 0.0f,
            convertWeightToPounds(response.weight) ?: 0.0f,
            convertWeightToOunces(response.weight) ?: 0.0f,
            convertHeightToMeters(response.height) ?: 0.0f,
            convertHeightToFeets(response.height) ?: 0.0f,
            ""
        )
    }

    private fun convertWeightToKg(hectograms: Int?): Float? = hectograms?.toFloat()?.div(10)

    private fun convertWeightToPounds(hectograms: Int?): Float? =
        convertWeightToKg(hectograms)?.times(2.20462f)

    private fun convertWeightToOunces(hectograms: Int?): Float? =
        convertWeightToKg(hectograms)?.times(35.274f)

    private fun convertHeightToMeters(decimeters: Int?): Float? = decimeters?.toFloat()?.div(10)

    private fun convertHeightToFeets(decimeters: Int?): Float? =
        convertHeightToMeters(decimeters)?.times(3.28084f)
}