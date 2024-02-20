package com.example.retrofitpokemon.data.domain.repository.remote.mapper.pokemon

import com.example.retrofitpokemon.data.domain.model.common.PokemonModel
import com.example.retrofitpokemon.data.domain.model.pokemon.ListPokemonModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.common.PokemonMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon.ListPokemonResponse

class ListPokemonMapper : ResponseMapper<ListPokemonResponse, ListPokemonModel> {
    override fun fromResponse(response: ListPokemonResponse): ListPokemonModel {

        val resultModel = arrayListOf<PokemonModel>()

        if (!response.results.isNullOrEmpty()) {
            val pokemonMapper = PokemonMapper()
            response.results.forEach { getListPokemonResultResponse ->
                resultModel.add(pokemonMapper.fromResponse(getListPokemonResultResponse))
            }
        }
        /*var id = 1
        for (rm in resultModel){
            rm.id = id
            id++
        }*/

        return ListPokemonModel(
            response.count ?: -1,
            response.next ?: "",
            response.previous ?: "",
            resultModel
        )
    }
}