package com.example.retrofitpokemon.ui.detail_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.AbilityFullDataModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.usecase.GetAbilityDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailFragmentViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getAbilityDetailUseCase: GetAbilityDetailUseCase
) : ViewModel() {

    private val _pokemonDetailFlow = MutableStateFlow(PokemonDetailModel())
    val pokemonDetailFlow: StateFlow<PokemonDetailModel> = _pokemonDetailFlow
    private val _abilitiesFlow = MutableStateFlow(mutableListOf(AbilityDetailModel()))
    val abilitiesFlow: StateFlow<List<AbilityDetailModel>> = _abilitiesFlow

    private var abilities: MutableList<AbilityDetailModel> = mutableListOf()

    fun getPokemonDetail(position: Int) {
        val idPokemon = position + 1
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonDetailUseCase(idPokemon).collect {
                _pokemonDetailFlow.value = it

                getAbilityDetail()
            }
        }
    }

    private suspend fun getAbilityDetail() {
        for (ability: AbilityFullDataModel in _pokemonDetailFlow.value.abilities) {
            getAbilityDetailUseCase(ability.ability.url).collect {
                abilities.add(it)
                _abilitiesFlow.value = abilities
            }
        }
    }
}