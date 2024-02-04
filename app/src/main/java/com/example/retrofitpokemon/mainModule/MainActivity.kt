package com.example.retrofitpokemon.mainModule

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitpokemon.APIService
import com.example.retrofitpokemon.databinding.ActivityMainBinding
import com.example.retrofitpokemon.models.PokemonModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mPokemons = mutableListOf<PokemonModel>()
    private lateinit var mAdapter: PokemonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()

        takePokemons()
    }

    private fun setupAdapter() {
        mAdapter = PokemonAdapter(mPokemons)
        val listLayout = LinearLayoutManager(this)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = listLayout
        binding.recyclerView.adapter = mAdapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private fun takePokemons() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getListPokemon(0, 0)
            val pokemonsResponse = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val pokemons = pokemonsResponse?.pokemons ?: emptyList()
                    mPokemons.clear()
                    mPokemons.addAll(pokemons)
                    mAdapter.notifyDataSetChanged()

                } else {
                    showError()
                }

            }

        }
    }

    private fun showError() {
        Toast.makeText(
            this, "No se ha cargado el percal",
            Toast.LENGTH_SHORT
        ).show()
    }
}