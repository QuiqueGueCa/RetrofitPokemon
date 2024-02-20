package com.example.retrofitpokemon.data.domain.model.error

import com.example.retrofitpokemon.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorModel(
    var error: String = "Unknown",
    var errorCode: String = "",
    var message: String = "Unknown"
) : BaseModel()