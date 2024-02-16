package com.example.retrofitpokemon.ui.list_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpokemon.data.domain.usecase.GetListPokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListFragmentViewModel(private val getListPokemonUseCase: GetListPokemonUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<ListFragmentUiState>(ListFragmentUiState.Loading)
    val uiState: StateFlow<ListFragmentUiState> = _uiState

    private var page = 0
    private val pageSize = 30
    private var namesList: ArrayList<String> = arrayListOf()
    fun getListPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            getListPokemonUseCase(pageSize, page * pageSize)
                .catch { ListFragmentUiState.Error(it.message.orEmpty()) }
                .collect {
                    val arrayList = ArrayList(it.results.map { model -> model.name })
                    namesList.addAll(arrayList)
                    _uiState.value = ListFragmentUiState.Success(namesList)
                }
            page++
        }
    }
}