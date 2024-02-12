package com.example.retrofitpokemon.ui.list_fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpokemon.data.domain.usecase.GetListPokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListFragmentViewModel(private val getListPokemonUseCase: GetListPokemonUseCase) :
    ViewModel() {

    private val listPokemonNamesMutableStateFlow =
        MutableStateFlow<ArrayList<String>>(arrayListOf())
    val listPokemonNamesStateFlow: StateFlow<ArrayList<String>> = listPokemonNamesMutableStateFlow

    fun getListPokemon() {
        Log.d("TAG", "l> getListPokemon")
        viewModelScope.launch(Dispatchers.IO) {
            getListPokemonUseCase(30, 0).collect {
                val arrayList = ArrayList(it.results.map { model -> model.name })
                Log.d(
                    "TAG",
                    "l> Tenemos una lista de ${arrayList.size} elementos: $arrayList vamos a emitirla"
                )
                listPokemonNamesMutableStateFlow.value = arrayList
            }
        }
    }
}