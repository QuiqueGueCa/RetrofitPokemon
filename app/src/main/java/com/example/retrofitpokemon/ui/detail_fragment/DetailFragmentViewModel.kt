package com.example.retrofitpokemon.ui.detail_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailFragmentViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _pokemonDetailFlow = MutableStateFlow(PokemonDetailModel())
    val pokemonDetailFlow: StateFlow<PokemonDetailModel> = _pokemonDetailFlow

    fun getPokemonDetail(position: Int) {
        val idPokemon = position + 1
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonDetailUseCase(idPokemon).collect {
                _pokemonDetailFlow.value = it
            }
        }
    }
}