package com.example.retrofitpokemon.ui.list_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpokemon.R
import com.example.retrofitpokemon.databinding.ItemPokemonBinding

class PokemonAdapter(
    private val dataSet: ArrayList<String>,
    private val listener: PokemonListener
) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    interface PokemonListener {
        fun onPokemonClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPokemonBinding.bind(view)
        fun setListener(position: Int) {
            binding.root.setOnClickListener {
                listener.onPokemonClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNamePokemon.text = dataSet[position]
        "#${position + 1}".also { holder.binding.tvIdPokemon.text = it }

        holder.setListener(position)
    }

    override fun getItemCount(): Int = dataSet.size

    fun refreshData(names: ArrayList<String>) {
        dataSet.clear()
        dataSet.addAll(names)
        notifyDataSetChanged()
    }
}
