package com.example.retrofitpokemon.mainModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitpokemon.models.PokemonModel
import com.example.retrofitpokemon.useCases.DataProvider

class MainViewModel : ViewModel() {

    private val dataProvider = DataProvider()
    var pokemons: LiveData<List<PokemonModel>> = dataProvider.pokemons

}