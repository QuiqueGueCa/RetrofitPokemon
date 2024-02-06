package com.example.retrofitpokemon.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitpokemon.data.domain.model.PokemonModel
import com.example.retrofitpokemon.useCases.DataProvider

class MainViewModel : ViewModel() {

    private val dataProvider = DataProvider()
    var pokemons: LiveData<List<PokemonModel>> = dataProvider.pokemons

}