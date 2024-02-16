package com.example.retrofitpokemon.data.domain.repository.remote.mapper.evolution_chain_detail

import com.example.retrofitpokemon.data.domain.model.common.SpeciesModel
import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.ChainModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.common.SpeciesMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.evolution_chain_detail.ChainResponse

class ChainMapper : ResponseMapper<ChainResponse, ChainModel> {

    private val pokemonBaseName: ArrayList<String> = arrayListOf()
    private val pokemonFirstEvoNames: ArrayList<String> = arrayListOf()
    private val pokemonSecondEvoNames: ArrayList<String> = arrayListOf()
    private val pokemonThirdEvoNames: ArrayList<String> = arrayListOf()
    override fun fromResponse(response: ChainResponse): ChainModel {

        addPokemonToLists(response)

        return ChainModel(
            pokemonBaseName,
            pokemonFirstEvoNames,
            pokemonSecondEvoNames,
            pokemonThirdEvoNames,
            createSpeciesModel(response)
        )
    }

    private fun addPokemonToLists(response: ChainResponse) {

        if (response.species != null) {
            pokemonBaseName.add(response.species!!.name!!.capitalize())
        }
        if (!response.evolvesTo.isNullOrEmpty()) {
            response.evolvesTo!!.forEach { evolvesToModel ->
                pokemonFirstEvoNames.add(evolvesToModel.species!!.name!!.capitalize())

                if (!evolvesToModel.evolvesTo.isNullOrEmpty()) {

                    evolvesToModel.evolvesTo!!.forEach { evolvesToModel2 ->

                        pokemonSecondEvoNames.add(evolvesToModel2.species!!.name!!.capitalize())

                        if (!evolvesToModel2.evolvesTo.isNullOrEmpty()) {
                            evolvesToModel2.evolvesTo!!.forEach { evolvesToModel3 ->

                                pokemonThirdEvoNames.add(evolvesToModel3.species!!.name!!.capitalize())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun createSpeciesModel(response: ChainResponse): SpeciesModel {
        val speciesMapper = SpeciesMapper()
        var speciesModel = SpeciesModel()

        if (response.species != null) {
            speciesModel = speciesMapper.fromResponse(response.species!!)
        }

        return speciesModel
    }
}