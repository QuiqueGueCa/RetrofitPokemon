package com.example.retrofitpokemon.ui.list_fragment

sealed class ListFragmentUiState {
    object Loading : ListFragmentUiState()
    data class Success(val listPokemon: ArrayList<String>) : ListFragmentUiState()
    data class Error(val msg: String) : ListFragmentUiState()
}
