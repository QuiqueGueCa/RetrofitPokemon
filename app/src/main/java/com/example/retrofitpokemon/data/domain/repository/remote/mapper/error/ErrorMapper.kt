package com.example.retrofitpokemon.data.domain.repository.remote.mapper.error

import com.example.retrofitpokemon.data.domain.model.error.ErrorModel
import com.example.retrofitpokemon.data.domain.repository.remote.mapper.ResponseMapper
import com.example.retrofitpokemon.data.domain.repository.remote.response.error.ErrorResponse

class ErrorMapper : ResponseMapper<ErrorResponse, ErrorModel> {

    override fun fromResponse(response: ErrorResponse): ErrorModel {
        return ErrorModel(response.error ?: "", response.errorCode ?: "", response.message ?: "")
    }
}
