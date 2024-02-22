package com.example.retrofitpokemon.ui.list_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpokemon.data.domain.model.error.ErrorModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitpokemon.data.domain.usecase.GetListPokemonUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListFragmentViewModel(
    private val getListPokemonUseCase: GetListPokemonUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<ListFragmentUiState>(ListFragmentUiState.Loading)
    val uiState: StateFlow<ListFragmentUiState> = _uiState

    private val listPokemonErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val listPokemonErrorSharedFlow: SharedFlow<ErrorModel> = listPokemonErrorMutableSharedFlow

    private val pokemonDetailErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val pokemonDetailErrorSharedFlow: SharedFlow<ErrorModel> = pokemonDetailErrorMutableSharedFlow

    private var page = 0
    private val pageSize = 21
    private var namesList: ArrayList<String> = arrayListOf()
    private var pokemonList: ArrayList<PokemonDetailModel> = arrayListOf()
    fun getListPokemon() {
        if (_uiState.value != ListFragmentUiState.Loading || page == 0) {
            _uiState.value = ListFragmentUiState.Loading

            viewModelScope.launch(Dispatchers.IO) {
                getListPokemonUseCase(pageSize, page * pageSize)
                    .collect {
                        when (it) {
                            is BaseResponse.Error -> {
                                //listPokemonErrorMutableSharedFlow.emit(it.error)
                                _uiState.value = ListFragmentUiState.Error(it.error.message)
                            }

                            is BaseResponse.Success -> {
                                val arrayList =
                                    ArrayList(it.data.results.map { model -> model.name })
                                namesList.addAll(arrayList)

                                addPokemonToList()

                                _uiState.value = ListFragmentUiState.Success(pokemonList)
                                page++
                            }
                        }
                    }
            }
        }
    }

    private suspend fun addPokemonToList() {
        var idPokemonToFind = 1 + (page * pageSize)
        val limit = (page * pageSize) + pageSize

        while (idPokemonToFind <= limit) {

            getPokemonDetailUseCase(idPokemonToFind).collect {
                when (it) {
                    is BaseResponse.Error -> {
                        pokemonDetailErrorMutableSharedFlow.emit(it.error)
                    }

                    is BaseResponse.Success -> {
                        pokemonList.add(it.data)
                        println(it.data.id)
                    }
                }
            }
            idPokemonToFind++
        }
    }
}