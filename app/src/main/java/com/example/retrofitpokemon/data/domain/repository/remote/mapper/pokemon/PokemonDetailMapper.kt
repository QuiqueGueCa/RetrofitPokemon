package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon

import com.example.retrofitpokemon.data.domain.model.pokemon.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon.SpritesModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon.PokemonDetailResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon.SpritesResponse
import java.text.DecimalFormat
import java.util.Locale

class PokemonDetailMapper : ResponseMapper<PokemonDetailResponse, PokemonDetailModel> {

    override fun fromResponse(response: PokemonDetailResponse): PokemonDetailModel {
        return PokemonDetailModel(
            response.id ?: -1,
            capitalizeName(response.name) ?: "",
            convertWeightToKg(response.weight) ?: "",
            convertWeightToPounds(response.weight) ?: "",
            convertWeightToOunces(response.weight) ?: "",
            convertHeightToMeters(response.height) ?: "",
            convertHeightToFeets(response.height) ?: "",
            setupSprite(response.sprites) ?: SpritesModel()
        )
    }

    private fun setupSprite(spritesResponse: SpritesResponse?): SpritesModel? {
        var sprite = ""

        if (!spritesResponse?.frontDefault.isNullOrBlank()) {
            sprite = spritesResponse?.frontDefault!!
        }
        return SpritesModel(sprite)
    }


    private fun capitalizeName(name: String?): String? {
        return name?.replaceFirstChar {
            if (it.isLowerCase()) {
                it.titlecase(Locale.getDefault())
            } else {
                it.toString()
            }
        }
    }

    private fun convertToString(numToConvert: Float?, mesureType: String): String? {
        val format = DecimalFormat()
        format.maximumFractionDigits = 2

        return format.format(numToConvert) + " " + mesureType
    }

    private fun convertWeightToKg(hectograms: Int?): String? {
        val kg = hectograms?.toFloat()?.div(10)

        return convertToString(kg, "Kg")
    }

    private fun convertWeightToPounds(hectograms: Int?): String? {
        val kg = hectograms?.toFloat()?.div(10)
        val pounds = kg?.times(2.20462f)
        return convertToString(pounds, "lb")
    }

    private fun convertWeightToOunces(hectograms: Int?): String? {
        val kg = hectograms?.toFloat()?.div(10)
        val ounces = kg?.times(35.274f)
        return convertToString(ounces, "Oz")
    }

    private fun convertHeightToMeters(decimeters: Int?): String? {
        val meters = decimeters?.toFloat()?.div(10)
        return convertToString(meters, "m")
    }

    private fun convertHeightToFeets(decimeters: Int?): String? {
        val meters = decimeters?.toFloat()?.div(10)
        val feets = meters?.times(3.28084f)
        return convertToString(feets, "ft")
    }
}