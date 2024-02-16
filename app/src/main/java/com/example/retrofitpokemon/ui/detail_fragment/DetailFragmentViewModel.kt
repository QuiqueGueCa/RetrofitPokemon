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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailFragmentViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getAbilityDetailUseCase: GetAbilityDetailUseCase,
    private val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase,
    private val getEvolutionChainDetailUseCase: GetEvolutionChainDetailUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow<DetailFragmentUiState>(DetailFragmentUiState.Loading)
    val uiState: StateFlow<DetailFragmentUiState> = _uiState


    private var evolutionChainUrl: String = ""
    private var pokemonDetailModel = PokemonDetailModel()
    private val evolutions = arrayListOf<String>()
    private val abilities: MutableList<AbilityDetailModel> = mutableListOf()


    fun getPokemonDetail(position: Int) {
        val idPokemon = position + 1
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonDetailUseCase(idPokemon)
                .catch { DetailFragmentUiState.Error(it.message.orEmpty()) }
                .collect {
                    pokemonDetailModel = it

                    getAbilityDetail()

                    getSpecies()

                    getEvolutionChain()

                    _uiState.value = DetailFragmentUiState.Success(
                        pokemonDetailModel, abilities, evolutions
                    )
                }
        }
    }

    private suspend fun getEvolutionChain() {
        getEvolutionChainDetailUseCase(evolutionChainUrl).collect {
            evolutions.add(it.chain.species.name.capitalize())

            if (!it.chain.evolvesTo.isNullOrEmpty()) {
                it.chain.evolvesTo.forEach { evolvesToModel ->

                    evolutions.add(evolvesToModel.species.name.capitalize())

                    if (!evolvesToModel.evolvesTo.isNullOrEmpty()) {
                        evolvesToModel.evolvesTo.forEach { evolvesToModel2 ->

                            evolutions.add(evolvesToModel2.species.name.capitalize())

                            if (!evolvesToModel2.evolvesTo.isNullOrEmpty()) {
                                evolvesToModel2.evolvesTo.forEach { evolvesToModel3 ->

                                    evolutions.add(evolvesToModel3.species.name.capitalize())
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun getSpecies() {
        getPokemonSpeciesUseCase(pokemonDetailModel.species.url).collect {
            evolutionChainUrl = it.evolutionChain.url
        }
    }

    private suspend fun getAbilityDetail() {
        for (ability: AbilityFullDataModel in pokemonDetailModel.abilities) {
            getAbilityDetailUseCase(ability.ability.url).collect {
                abilities.add(it)
            }
        }
    }
}