package com.example.retrofitpokemon.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitpokemon.data.domain.usecase.GetListPokemonUseCase
import com.example.retrofitpokemon.databinding.ActivityMainBinding
import com.example.retrofitpokemon.injection.InjectionSingleton
import com.example.retrofitpokemon.ui.adapter.PokemonAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: PokemonAdapter
    private val mViewModel: MainViewModel =
        MainViewModel(GetListPokemonUseCase(InjectionSingleton.provideDataSource()))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()

        setupViewModel()
        mViewModel.getListPokemon()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewModel() {
        lifecycleScope.launch {
            mViewModel.listPokemonNamesStateFlow.collect { dataSet ->
                Log.d("TAG", "l> Observamos y refrescamos las lista con ${dataSet.size} elementos")
                mAdapter.refreshData(dataSet)
            }
        }
    }

    private fun setupAdapter() {
        mAdapter = PokemonAdapter(arrayListOf())
        val listLayout = LinearLayoutManager(this)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = listLayout
        binding.recyclerView.adapter = mAdapter
    }
}