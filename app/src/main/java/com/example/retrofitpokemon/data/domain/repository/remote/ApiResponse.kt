package com.example.retrofitpokemon.data.domain.repository.remote

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error<out T>(
        val exception: Exception,
        val message: String? = exception.localizedMessage
    ) : ApiResponse<Nothing>()

}
