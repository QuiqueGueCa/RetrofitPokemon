package com.example.retrofitpokemon.ui.detail_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.error.ErrorModel
import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.EvolutionChainDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.AbilityFullDataModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitpokemon.data.domain.usecase.GetAbilityDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetEvolutionChainDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonSpeciesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailFragmentViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getAbilityDetailUseCase: GetAbilityDetailUseCase,
    private val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase,
    private val getEvolutionChainDetailUseCase: GetEvolutionChainDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailFragmentUiState>(DetailFragmentUiState.Loading)
    val uiState: StateFlow<DetailFragmentUiState> = _uiState

    private val pokemonDetailErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val pokemonDetailErrorSharedFlow: SharedFlow<ErrorModel> = pokemonDetailErrorMutableSharedFlow

    private val evolutionChainErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val evolutionChainErrorSharedFlow: SharedFlow<ErrorModel> = evolutionChainErrorMutableSharedFlow

    private val speciesErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val speciesErrorSharedFlow: SharedFlow<ErrorModel> = speciesErrorMutableSharedFlow

    private val abilityDetailErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val abilityDetailErrorSharedFlow: SharedFlow<ErrorModel> = abilityDetailErrorMutableSharedFlow


    private var evolutionChainUrl: String = ""
    private var pokemonDetailModel = PokemonDetailModel()
    private var evolutionChainDetailModel = EvolutionChainDetailModel()
    private val abilities: MutableList<AbilityDetailModel> = mutableListOf()


    fun getPokemonDetail(idPokemon: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonDetailUseCase(idPokemon)
                .collect {
                    when (it) {
                        is BaseResponse.Error -> {
                            //pokemonDetailErrorMutableSharedFlow.emit(it.error)
                            _uiState.value = DetailFragmentUiState.Error(it.error.message)
                        }

                        is BaseResponse.Success -> {
                            pokemonDetailModel = it.data
                            getAbilityDetail()

                            getSpecies()

                            getEvolutionChain()

                            _uiState.value = DetailFragmentUiState.Success(
                                pokemonDetailModel, abilities, evolutionChainDetailModel
                            )
                        }
                    }
                }
        }
    }

    private suspend fun getEvolutionChain() {
        getEvolutionChainDetailUseCase(evolutionChainUrl).collect {
            when (it) {
                is BaseResponse.Error -> {
                    evolutionChainErrorMutableSharedFlow.emit(it.error)
                }

                is BaseResponse.Success -> {
                    evolutionChainDetailModel = it.data
                }
            }
        }
    }

    private suspend fun getSpecies() {
        getPokemonSpeciesUseCase(pokemonDetailModel.species.url).collect {
            when (it) {
                is BaseResponse.Error -> {
                    speciesErrorMutableSharedFlow.emit(it.error)
                }

                is BaseResponse.Success -> {
                    evolutionChainUrl = it.data.evolutionChain.url
                }
            }
        }
    }

    private suspend fun getAbilityDetail() {
        for (ability: AbilityFullDataModel in pokemonDetailModel.abilities) {
            getAbilityDetailUseCase(ability.ability.url).collect {
                when (it) {
                    is BaseResponse.Error -> {
                        abilityDetailErrorMutableSharedFlow.emit(it.error)
                    }

                    is BaseResponse.Success -> {
                        abilities.add(it.data)
                    }
                }

            }
        }
    }
}