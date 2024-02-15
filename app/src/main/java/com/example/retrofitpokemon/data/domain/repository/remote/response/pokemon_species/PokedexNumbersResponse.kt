package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_species

import com.google.gson.annotations.SerializedName


data class PokedexNumbersResponse(

    @SerializedName("entry_number") var entryNumber: Int?,
    @SerializedName("pokedex") var pokedex: PokedexResponse?

)