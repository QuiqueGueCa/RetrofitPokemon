package com.example.retrofitpokemon.ui.detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofitpokemon.R
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.usecase.GetAbilityDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetEvolutionChainDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonSpeciesUseCase
import com.example.retrofitpokemon.databinding.FragmentDetailBinding
import com.example.retrofitpokemon.databinding.ItemEvolutionBinding
import com.example.retrofitpokemon.injection.InjectionSingleton
import com.example.retrofitpokemon.ui.detail_fragment.adapter.AbilityAdapter
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private lateinit var mAdapter: AbilityAdapter
    private val args: DetailFragmentArgs by navArgs()
    private val mViewModel: DetailFragmentViewModel =
        DetailFragmentViewModel(
            GetPokemonDetailUseCase(InjectionSingleton.provideDataSource()),
            GetAbilityDetailUseCase(InjectionSingleton.provideDataSource()),
            GetPokemonSpeciesUseCase(InjectionSingleton.provideDataSource()),
            GetEvolutionChainDetailUseCase(InjectionSingleton.provideDataSource())
        )

    private lateinit var mBinding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentDetailBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupAdapter()

        setupViewModel()

        mViewModel.getPokemonDetail(args.position)
    }

    private fun setupAdapter() {
        mAdapter = AbilityAdapter(mutableListOf())

        with(mBinding) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = mAdapter
        }
    }

    private fun setupViewModel() {

        lifecycleScope.launch {
            mViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is DetailFragmentUiState.Error -> {
                        mBinding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Ha ocurrido un error: ${uiState.msg}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    DetailFragmentUiState.Loading -> {
                        mBinding.progressBar.visibility = View.VISIBLE
                    }

                    is DetailFragmentUiState.Success -> {
                        mBinding.progressBar.visibility = View.GONE

                        setupPokemonMainData(uiState.pokemonDetailModel)
                        mAdapter.refreshData(uiState.abilities)
                        showEvolutionChain(uiState.evolutionChain)
                    }
                }
            }
        }
    }

    private fun showEvolutionChain(evolutionChain: ArrayList<String>) {
        for (namePokemon in evolutionChain) {
            val itemBinding = ItemEvolutionBinding.inflate(layoutInflater)
            mBinding.llHorizontal.addView(itemBinding.root)

            if (namePokemon == mBinding.tvName.text) {
                val color = ResourcesCompat.getColor(resources, R.color.green, null)
                itemBinding.root.setTextColor(color)
            }
            itemBinding.root.text =
                (evolutionChain.indexOf(namePokemon) + 1).toString() + " " + namePokemon

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            layoutParams.setMargins(4, 0, 4, 0)

            itemBinding.root.layoutParams = layoutParams
        }
    }

    private fun setupPokemonMainData(pokemonDetailModel: PokemonDetailModel) {
        with(mBinding) {
            tvName.text = pokemonDetailModel.name
            tvWeightKg.text = pokemonDetailModel.weightKg
            tvWeightPounds.text = pokemonDetailModel.weightLb
            tvWeightOunces.text = pokemonDetailModel.weightOz
            tvHeightFeets.text = pokemonDetailModel.heightFeets
            tvHeightMeters.text = pokemonDetailModel.heightMeters
        }
        Glide.with(requireContext())
            .load(pokemonDetailModel.spritesModel.sprite)
            .apply(RequestOptions().centerCrop())
            .into(mBinding.imgPokemon)
    }
}