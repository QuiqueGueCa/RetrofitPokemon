package com.example.retrofitpokemon

import com.google.gson.annotations.SerializedName

class PokemonResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
}