package com.example.retrofitpokemon.mainModule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpokemon.R
import com.example.retrofitpokemon.databinding.ItemPokemonBinding
import com.example.retrofitpokemon.models.PokemonModel

class PokemonAdapter(private val pokemons: MutableList<PokemonModel>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPokemonBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNamePokemon.text = pokemons[position].name
    }

    override fun getItemCount(): Int = pokemons.size
}