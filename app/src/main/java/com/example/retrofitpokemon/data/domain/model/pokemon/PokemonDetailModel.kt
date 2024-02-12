package com.example.retrofitpokemon.data.domain.model.pokemon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailModel(
    val id: Int = -1,
    val name: String = "",
    val weightKg: Float = 0.0f,
    val weightLb: Float = 0.0f,
    val weightOz: Float = 0.0f,
    val heightMeters: Float = 0.0f,
    val heightFeets: Float = 0.0f,
    val img: String = ""
) : Parcelable