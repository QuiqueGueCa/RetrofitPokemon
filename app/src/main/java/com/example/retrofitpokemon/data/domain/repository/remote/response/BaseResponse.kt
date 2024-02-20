package com.example.retrofitpokemon.data.domain.repository.remote.response

sealed class BaseResponse<T> {
    data class Success<T>(val data: T) : BaseResponse<T>()
    data class Error<T>(
        val exception: Exception,
        val message: String? = exception.localizedMessage
    ) : BaseResponse<Nothing>()
}
