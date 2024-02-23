package com.example.retrofitpokemon.ui.detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofitpokemon.R
import com.example.retrofitpokemon.data.domain.model.ability_detail.AbilityDetailModel
import com.example.retrofitpokemon.data.domain.model.evolution_chain_detail.EvolutionChainDetailModel
import com.example.retrofitpokemon.data.domain.model.pokemon_detail.PokemonDetailModel
import com.example.retrofitpokemon.data.domain.usecase.GetAbilityDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetEvolutionChainDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonDetailUseCase
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonSpeciesUseCase
import com.example.retrofitpokemon.databinding.FragmentDetailBinding
import com.example.retrofitpokemon.databinding.ItemAbilityBinding
import com.example.retrofitpokemon.databinding.ItemEvolutionBinding
import com.example.retrofitpokemon.injection.InjectionSingleton
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

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
        Glide.with(requireContext()).asGif().load(R.drawable.pokeball).into(mBinding.imgPokeball);

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupViewModel()

        mViewModel.getPokemonDetail(args.position)
    }


    private fun setupViewModel() {

        uiStateBehaviour()

        showLoadingErrors()
    }

    private fun showLoadingErrors() {
        lifecycleScope.launch {
            mViewModel.pokemonDetailErrorSharedFlow.collect { error ->
                Toast.makeText(
                    requireContext(),
                    error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        lifecycleScope.launch {
            mViewModel.speciesErrorSharedFlow.collect { error ->
                Toast.makeText(
                    requireContext(),
                    error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        lifecycleScope.launch {
            mViewModel.abilityDetailErrorSharedFlow.collect { error ->
                Toast.makeText(
                    requireContext(),
                    error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        lifecycleScope.launch {
            mViewModel.evolutionChainErrorSharedFlow.collect { error ->
                Toast.makeText(
                    requireContext(),
                    error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun uiStateBehaviour() {
        lifecycleScope.launch {
            mViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is DetailFragmentUiState.Error -> {
                        mBinding.imgPokeball.visibility = View.GONE
                        mBinding.constraintLayout.visibility = View.INVISIBLE
                        Toast.makeText(
                            requireContext(),
                            "Ha ocurrido un error: ${uiState.msg}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    DetailFragmentUiState.Loading -> {
                        mBinding.constraintLayout.visibility = View.INVISIBLE
                        mBinding.imgPokeball.visibility = View.VISIBLE
                    }

                    is DetailFragmentUiState.Success -> {
                        mBinding.constraintLayout.visibility = View.VISIBLE
                        mBinding.imgPokeball.visibility = View.GONE

                        setupPokemonMainData(uiState.pokemonDetailModel)
                        showAbilities(uiState.abilities)
                        showEvolutionChain(uiState.evolutionChainDetailModel)
                    }
                }
            }
        }
    }

    private fun showAbilities(abilities: MutableList<AbilityDetailModel>) {
        for (ability in abilities) {
            val itemBinding = ItemAbilityBinding.inflate(layoutInflater)
            mBinding.llAbilities.addView(itemBinding.root)
            val param = itemBinding.root.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(0, 16, 0, 16)

            with(itemBinding) {
                root.layoutParams = param
                tvNameAbility.text = ability.name
                tvDescriptionAbility.text = ability.effectEntriesModel.effect
            }
        }
    }

    private fun showEvolutionChain(evolutionChainDetailModel: EvolutionChainDetailModel) {

        showList(evolutionChainDetailModel.chain.pokemonBaseName, "Base:")
        showList(evolutionChainDetailModel.chain.pokemonFirstEvoNames, "1ª evolución:")
        showList(evolutionChainDetailModel.chain.pokemonSecondEvoNames, "2ª evolución:")
        showList(evolutionChainDetailModel.chain.pokemonThirdEvoNames, "3ª evolución:")
    }

    private fun showList(namesList: ArrayList<String>, mainText: String) {
        for (namePokemon in namesList) {
            val itemBinding = ItemEvolutionBinding.inflate(layoutInflater)
            mBinding.llHorizontal.addView(itemBinding.root)

            if (namePokemon == mBinding.tvName.text) {
                val color = ResourcesCompat.getColor(resources, R.color.yellowPokemon, null)
                itemBinding.root.setTextColor(color)
            }
            "$mainText $namePokemon".also { itemBinding.root.text = it }

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

        if (pokemonDetailModel.types.isNotEmpty()) {
            setupTypeIcon(pokemonDetailModel.types[0].typeModel.name, mBinding.imgFirstType)
        }

        if (pokemonDetailModel.types.size == 2) {
            setupTypeIcon(pokemonDetailModel.types[1].typeModel.name, mBinding.imgSecondType)
        }
    }

    private fun setupTypeIcon(type: String, imgType: ImageView) {
        when (type) {
            "normal" -> {
                imgType.setImageResource(R.drawable.ic_normal)
            }

            "fighting" -> {
                imgType.setImageResource(R.drawable.ic_fighting)
            }

            "flying" -> {
                imgType.setImageResource(R.drawable.ic_flying)
            }

            "poison" -> {
                imgType.setImageResource(R.drawable.ic_poison)
            }

            "ground" -> {
                imgType.setImageResource(R.drawable.ic_ground)
            }

            "rock" -> {
                imgType.setImageResource(R.drawable.ic_rock)
            }

            "bug" -> {
                imgType.setImageResource(R.drawable.ic_bug)
            }

            "ghost" -> {
                imgType.setImageResource(R.drawable.ic_ghost)
            }

            "steel" -> {
                imgType.setImageResource(R.drawable.ic_steel)
            }

            "fire" -> {
                imgType.setImageResource(R.drawable.ic_fire)
            }

            "water" -> {
                imgType.setImageResource(R.drawable.ic_water)
            }

            "grass" -> {
                imgType.setImageResource(R.drawable.ic_grass)
            }

            "electric" -> {
                imgType.setImageResource(R.drawable.ic_electric)
            }

            "psychic" -> {
                imgType.setImageResource(R.drawable.ic_psychic)
            }

            "ice" -> {
                imgType.setImageResource(R.drawable.ic_ice)
            }

            "dragon" -> {
                imgType.setImageResource(R.drawable.ic_dragon)
            }

            "dark" -> {
                imgType.setImageResource(R.drawable.ic_dark)
            }

            "fairy" -> {
                imgType.setImageResource(R.drawable.ic_fairy)
            }

            "unknown" -> {
                imgType.setImageResource(R.drawable.ic_normal)
            }

            "shadow" -> {
                imgType.setImageResource(R.drawable.ic_shadow)
            }
        }
    }
}