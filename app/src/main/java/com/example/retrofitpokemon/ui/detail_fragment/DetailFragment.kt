package com.example.retrofitpokemon.ui.detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofitpokemon.data.domain.model.pokemon.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonDetailUseCase
import com.example.retrofitpokemon.databinding.FragmentDetailBinding
import com.example.retrofitpokemon.injection.InjectionSingleton
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private val mViewModel: DetailFragmentViewModel =
        DetailFragmentViewModel(GetPokemonDetailUseCase(InjectionSingleton.provideDataSource()))

    //private lateinit var pokemonDetailModel: PokemonDetailModel
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

        setupViewModel()

        mViewModel.getPokemonDetail(args.position)
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            mViewModel.pokemonDetailFlow.collect {
                setupData(it)
            }
        }
    }

    private fun setupData(pokemonDetailModel: PokemonDetailModel) {
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