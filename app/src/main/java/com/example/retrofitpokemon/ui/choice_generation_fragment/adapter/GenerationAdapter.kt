package com.example.retrofitpokemon.ui.choice_generation_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpokemon.R
import com.example.retrofitpokemon.databinding.ItemGenerationBinding

class GenerationAdapter(
    private val generations: MutableList<Any>,
    private val listener: GenerationListener
) : RecyclerView.Adapter<GenerationAdapter.ViewHolder>() {


    interface GenerationListener {
        fun onGenerationClick()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemGenerationBinding.bind(view)

        fun setListener() {
            binding.root.setOnClickListener {
                listener.onGenerationClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_generation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount() = generations.count()
}