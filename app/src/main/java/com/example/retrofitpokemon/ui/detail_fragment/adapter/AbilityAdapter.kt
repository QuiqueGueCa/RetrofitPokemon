package com.example.retrofitpokemon.ui.detail_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpokemon.R
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.AbilityFullDataModel
import com.example.retrofitpokemon.databinding.ItemAbilityBinding

class AbilityAdapter(private val abilities: MutableList<AbilityFullDataModel>) :
    RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAbilityBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ability, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNameAbility.text = abilities[position].ability.name
    }

    override fun getItemCount(): Int = abilities.count()

    fun refreshData(dataSet: MutableList<AbilityFullDataModel>) {
        abilities.clear()
        abilities.addAll(dataSet)
        notifyDataSetChanged()
    }
}