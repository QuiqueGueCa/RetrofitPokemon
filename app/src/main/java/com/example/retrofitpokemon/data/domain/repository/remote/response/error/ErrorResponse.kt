package com.example.retrofitpokemon.data.domain.repository.remote.response.error

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error") var error: String?,
    @SerializedName("errorCode") var errorCode: String?,
    @SerializedName("message") var message: String?,
) {
    //Para qué el constructor?
    constructor(errorResponse: ErrorResponse?) : this(
        errorResponse?.error,
        errorResponse?.errorCode,
        errorResponse?.message
    )
}