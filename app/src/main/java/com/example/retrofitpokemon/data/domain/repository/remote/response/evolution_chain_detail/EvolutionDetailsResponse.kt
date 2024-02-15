package com.example.retrofitpokemon.data.domain.repository.remote.response.evolution_chain_detail

import com.google.gson.annotations.SerializedName


data class EvolutionDetailsResponse(

    @SerializedName("gender") var gender: String?,
    @SerializedName("held_item") var heldItem: String?,
    @SerializedName("item") var item: String?,
    @SerializedName("known_move") var knownMove: String?,
    @SerializedName("known_move_type") var knownMoveType: String?,
    @SerializedName("location") var location: String?,
    @SerializedName("min_affection") var minAffection: String?,
    @SerializedName("min_beauty") var minBeauty: String?,
    @SerializedName("min_happiness") var minHappiness: String?,
    @SerializedName("min_level") var minLevel: Int?,
    @SerializedName("needs_overworld_rain") var needsOverworldRain: Boolean?,
    @SerializedName("party_species") var partySpecies: String?,
    @SerializedName("party_type") var partyType: String?,
    @SerializedName("relative_physical_stats") var relativePhysicalStats: String?,
    @SerializedName("time_of_day") var timeOfDay: String?,
    @SerializedName("trade_species") var tradeSpecies: String?,
    @SerializedName("trigger") var trigger: TriggerResponse?,
    @SerializedName("turn_upside_down") var turnUpsideDown: Boolean?

)