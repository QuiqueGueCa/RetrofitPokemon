package com.example.retrofitpokemon.mainModule

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitpokemon.databinding.ActivityMainBinding
import com.example.retrofitpokemon.models.PokemonModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mPokemons = mutableListOf<PokemonModel>()
    private lateinit var mAdapter: PokemonAdapter
    private lateinit var mViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()

        setupViewModel()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewModel() {
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mViewModel.pokemons.observe(this) {
            mPokemons.clear()
            mPokemons.addAll(it)
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun setupAdapter() {
        mAdapter = PokemonAdapter(mPokemons)
        val listLayout = LinearLayoutManager(this)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = listLayout
        binding.recyclerView.adapter = mAdapter
    }
}