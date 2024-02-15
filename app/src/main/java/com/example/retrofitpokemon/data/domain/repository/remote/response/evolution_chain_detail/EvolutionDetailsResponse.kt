package com.example.retrofitpokemon.data.domain.repository.remote.response.evolution_chain_detail

import com.google.gson.annotations.SerializedName


data class EvolutionDetailsResponse(

    @SerializedName("gender") var gender: Any?,
    @SerializedName("held_item") var heldItem: Any?,
    @SerializedName("item") var item: Any?,
    @SerializedName("known_move") var knownMove: Any?,
    @SerializedName("known_move_type") var knownMoveType: Any?,
    @SerializedName("location") var location: Any?,
    @SerializedName("min_affection") var minAffection: Any?,
    @SerializedName("min_beauty") var minBeauty: Any?,
    @SerializedName("min_happiness") var minHappiness: Any?,
    @SerializedName("min_level") var minLevel: Int?,
    @SerializedName("needs_overworld_rain") var needsOverworldRain: Boolean?,
    @SerializedName("party_species") var partySpecies: Any?,
    @SerializedName("party_type") var partyType: Any?,
    @SerializedName("relative_physical_stats") var relativePhysicalStats: Any?,
    @SerializedName("time_of_day") var timeOfDay: Any?,
    @SerializedName("trade_species") var tradeSpecies: Any?,
    @SerializedName("trigger") var trigger: TriggerResponse?,
    @SerializedName("turn_upside_down") var turnUpsideDown: Boolean?

)