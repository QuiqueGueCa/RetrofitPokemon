package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon_detail

import com.example.retrofitpokemon.data.domain.model.common.SpeciesModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.AbilityFullDataModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.SpritesModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.TypeDetailModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.common.SpeciesMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.common.SpeciesResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.AbilityFullDataResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.PokemonDetailResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.SpritesResponse
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_detail.TypeDetailResponse
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
            setupSprite(response.sprites),
            setupAbilities(response.abilities),
            setupSpecies(response.species),
            setupTypeList(response.types)
        )
    }

    private fun setupTypeList(types: List<TypeDetailResponse>?): MutableList<TypeDetailModel> {
        val typeDetailMapper = TypeDetailMapper()
        val listTypeDetailModel = mutableListOf<TypeDetailModel>()

        if (!types.isNullOrEmpty()) {
            types.forEach {
                listTypeDetailModel.add(typeDetailMapper.fromResponse(it))
            }
        }
        return listTypeDetailModel
    }

    private fun setupSpecies(species: SpeciesResponse?): SpeciesModel {
        val speciesMapper = SpeciesMapper()
        var speciesModel = SpeciesModel()
        if (species != null) {
            speciesModel = speciesMapper.fromResponse(species)
        }
        return speciesModel
    }

    private fun setupAbilities(
        abilityFullDataResponseList: MutableList<AbilityFullDataResponse>?
    ): MutableList<AbilityFullDataModel> {
        val abilityFullDataMapper = AbilityFullDataMapper()
        val abilityFullDataModelList = mutableListOf<AbilityFullDataModel>()
        if (!abilityFullDataResponseList.isNullOrEmpty()) {
            abilityFullDataResponseList.forEach {
                abilityFullDataModelList.add(abilityFullDataMapper.fromResponse(it))
            }
        }
        return abilityFullDataModelList
    }

    private fun setupSprite(spritesResponse: SpritesResponse?): SpritesModel {
        var spritesModel = SpritesModel()
        val spritesMapper = SpritesMapper()

        if (spritesResponse != null) {
            spritesModel = spritesMapper.fromResponse(spritesResponse)
        }
        return spritesModel
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