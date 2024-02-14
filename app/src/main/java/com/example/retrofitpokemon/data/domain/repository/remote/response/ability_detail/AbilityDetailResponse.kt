package com.example.retrofitpokemon.data.domain.repository.remote.response.ability_detail

import com.google.gson.annotations.SerializedName

data class AbilityDetailResponse(
    @SerializedName("effect_changes")
    val effectChanges: List<Any>?,
    @SerializedName("effect_entries")
    val effectEntries: MutableList<EffectEntriesResponse>?,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<Any>?,
    @SerializedName("generation")
    val generation: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_main_series")
    val isMainSeries: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("names")
    val names: List<Any>?,
    @SerializedName("pokemon")
    val pokemon: List<Any>?,
)
