package com.example.retrofitpokemon.data.domain.repository.remote.response.pokemon_species

import com.google.gson.annotations.SerializedName


data class PokemonSpeciesResponse(

    @SerializedName("base_happiness") var baseHappiness: Int?,
    @SerializedName("capture_rate") var captureRate: Int?,
    @SerializedName("color") var color: ColorResponse?,
    @SerializedName("egg_groups") var eggGroups: ArrayList<EggGroupsResponse>?,
    @SerializedName("evolution_chain") var evolutionChain: EvolutionChainResponse?,
    @SerializedName("evolves_from_species") var evolvesFromSpecies: String?,
    @SerializedName("flavor_text_entries") var flavorTextEntries: ArrayList<FlavorTextEntriesResponse>?,
    @SerializedName("form_descriptions") var formDescriptions: ArrayList<String>?,
    @SerializedName("forms_switchable") var formsSwitchable: Boolean?,
    @SerializedName("gender_rate") var genderRate: Int?,
    @SerializedName("genera") var genera: ArrayList<GeneraResponse>?,
    @SerializedName("generation") var generation: GenerationResponse?,
    @SerializedName("growth_rate") var growthRate: GrowthRateResponse?,
    @SerializedName("habitat") var habitat: HabitatResponse?,
    @SerializedName("has_gender_differences") var hasGenderDifferences: Boolean?,
    @SerializedName("hatch_counter") var hatchCounter: Int?,
    @SerializedName("id") var id: Int?,
    @SerializedName("is_baby") var isBaby: Boolean?,
    @SerializedName("is_legendary") var isLegendary: Boolean?,
    @SerializedName("is_mythical") var isMythical: Boolean?,
    @SerializedName("name") var name: String?,
    @SerializedName("names") var names: ArrayList<NamesResponse>?,
    @SerializedName("order") var order: Int?,
    @SerializedName("pal_park_encounters") var palParkEncounters: ArrayList<PalParkEncountersResponse>,
    @SerializedName("pokedex_numbers") var pokedexNumbers: ArrayList<PokedexNumbersResponse>,
    @SerializedName("shape") var shape: ShapeResponse?,
    @SerializedName("varieties") var varieties: ArrayList<VarietiesResponse>?

)