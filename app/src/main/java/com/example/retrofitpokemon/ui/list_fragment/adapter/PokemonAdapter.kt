package com.example.retrofitpokemon.ui.list_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofitpokemon.R
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.databinding.ItemPokemonBinding

class PokemonAdapter(
    private val dataSet: ArrayList<PokemonDetailModel>,
    private val listener: PokemonListener,
    private val context: Context
) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    interface PokemonListener {
        fun onPokemonClick(idPokemon: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPokemonBinding.bind(view)
        fun setListener(position: Int) {
            binding.root.setOnClickListener {
                listener.onPokemonClick(dataSet[position].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding) {
            tvIdPokemon.text = "#${dataSet[position].id}"
            tvNamePokemon.text = dataSet[position].name
            Glide.with(context)
                .load(dataSet[position].spritesModel.sprite)
                .apply(RequestOptions().centerCrop())
                .into(this.imgPokemon)
        }

        holder.setListener(position)
    }

    override fun getItemCount(): Int = dataSet.size

    fun refreshData(pokemonList: ArrayList<PokemonDetailModel>) {
        dataSet.clear()
        dataSet.addAll(pokemonList)
        notifyDataSetChanged()
    }
}
