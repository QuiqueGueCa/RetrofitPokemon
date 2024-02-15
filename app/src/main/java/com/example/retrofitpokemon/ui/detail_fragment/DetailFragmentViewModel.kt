package com.example.retrofitpokemon.ui.detail_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.AbilityFullDataModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.usecase.GetAbilityDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetEvolutionChainDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonSpeciesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailFragmentViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getAbilityDetailUseCase: GetAbilityDetailUseCase,
    private val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase,
    private val getEvolutionChainDetailUseCase: GetEvolutionChainDetailUseCase
) : ViewModel() {

    private val _pokemonDetailStateFlow = MutableStateFlow(PokemonDetailModel())
    val pokemonDetailStateFlow: StateFlow<PokemonDetailModel> = _pokemonDetailStateFlow

    private val _abilitiesStateFlow = MutableStateFlow(mutableListOf(AbilityDetailModel()))
    val abilitiesStateFlow: StateFlow<MutableList<AbilityDetailModel>> = _abilitiesStateFlow

    private val _evolutionChainStateFlow = MutableStateFlow("")
    val evolutionChainStateFlow: StateFlow<String> = _evolutionChainStateFlow

    private var evolutionChainUrl: String = ""


    fun getPokemonDetail(position: Int) {
        val idPokemon = position + 1
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonDetailUseCase(idPokemon).collect {
                _pokemonDetailStateFlow.value = it

                getAbilityDetail()

                getSpecies()

                getEvolutionChain()
            }
        }
    }

    private suspend fun getEvolutionChain() {
        val evolutions = arrayListOf<String>()
        var evolutionsInString = ""
        getEvolutionChainDetailUseCase(evolutionChainUrl).collect {
            evolutions.add(it.chain.species.name)

            if (!it.chain.evolvesTo.isNullOrEmpty()) {
                it.chain.evolvesTo.forEach { evolvesToModel ->

                    evolutions.add(evolvesToModel.species.name)

                    if (!evolvesToModel.evolvesTo.isNullOrEmpty()) {
                        evolvesToModel.evolvesTo.forEach { evolvesToModel2 ->

                            evolutions.add(evolvesToModel2.species.name)

                            if (!evolvesToModel2.evolvesTo.isNullOrEmpty()) {
                                evolvesToModel2.evolvesTo.forEach { evolvesToModel3 ->

                                    evolutions.add(evolvesToModel3.species.name)
                                }
                            }
                        }
                    }
                }
            }

            for (i in 0..<evolutions.size) {
                if (i < evolutions.size - 1) {

                    evolutionsInString += evolutions[i] + " -> "

                } else {

                    evolutionsInString += evolutions[i]
                }
            }
            _evolutionChainStateFlow.value = evolutionsInString
        }
    }

    private suspend fun getSpecies() {
        getPokemonSpeciesUseCase(_pokemonDetailStateFlow.value.species.url).collect {
            //println(it.evolutionChain.url)
            evolutionChainUrl = it.evolutionChain.url
        }
    }

    private suspend fun getAbilityDetail() {
        val abilities: MutableList<AbilityDetailModel> = mutableListOf()
        for (ability: AbilityFullDataModel in _pokemonDetailStateFlow.value.abilities) {
            getAbilityDetailUseCase(ability.ability.url).collect {
                abilities.add(it)
            }
        }
        _abilitiesStateFlow.value = abilities
    }
}