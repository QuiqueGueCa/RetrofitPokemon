package com.example.retrofitpokemon.ui.list_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpokemon.data.domain.model.error.ErrorModel
import com.example.retrofitpokemon.data.domain.repository.remote.response.BaseResponse
import com.example.retrofitpokemon.data.domain.usecase.GetListPokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListFragmentViewModel(private val getListPokemonUseCase: GetListPokemonUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<ListFragmentUiState>(ListFragmentUiState.Loading)
    val uiState: StateFlow<ListFragmentUiState> = _uiState

    private val listPokemonErrorMutableSharedFlow = MutableSharedFlow<ErrorModel>()
    val listPokemonErrorSharedFlow: SharedFlow<ErrorModel> = listPokemonErrorMutableSharedFlow

    private var page = -1
    private val pageSize = 30
    private var namesList: ArrayList<String> = arrayListOf()
    fun getListPokemon() {
        if (_uiState.value != ListFragmentUiState.Loading || page == -1) {
            page++
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
                                _uiState.value = ListFragmentUiState.Success(namesList)
                            }
                        }
                    }
            }
        }
    }
}