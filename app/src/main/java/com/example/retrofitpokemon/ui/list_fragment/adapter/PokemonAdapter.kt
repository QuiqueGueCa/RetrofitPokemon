package com.example.retrofitpokemon.ui.list_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpokemon.R
import com.example.retrofitpokemon.databinding.ItemPokemonBinding

class PokemonAdapter(private val dataSet: ArrayList<String>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPokemonBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNamePokemon.text = dataSet[position]
    }

    override fun getItemCount(): Int = dataSet.size

    fun refreshData(names: ArrayList<String>) {
        dataSet.clear()
        dataSet.addAll(names)
        notifyDataSetChanged()
    }
}
