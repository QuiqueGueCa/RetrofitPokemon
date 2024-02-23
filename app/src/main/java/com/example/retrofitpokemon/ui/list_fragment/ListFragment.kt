package com.example.retrofitpokemon.ui.list_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpokemon.data.domain.usecase.GetPokemonDetailUseCase
import com.example.retrofitpokemon.databinding.FragmentListBinding
import com.example.retrofitpokemon.injection.InjectionSingleton
import com.example.retrofitpokemon.ui.list_fragment.adapter.PokemonAdapter
import kotlinx.coroutines.launch

class ListFragment : Fragment(), PokemonAdapter.PokemonListener {

    private lateinit var mBinding: FragmentListBinding
    private lateinit var mAdapter: PokemonAdapter
    private val mViewModel: ListFragmentViewModel =
        ListFragmentViewModel(
            GetPokemonDetailUseCase(InjectionSingleton.provideDataSource())
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.addPokemonToList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentListBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupViewModel()
        setupScrollListener()
    }

    private fun setupViewModel() {

        uiStateBehaviour()
        showLoadingError()
    }

    private fun showLoadingError() {

        lifecycleScope.launch {
            mViewModel.pokemonDetailErrorSharedFlow.collect { error ->
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
                    is ListFragmentUiState.Error -> {
                        mBinding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Ha ocurrido un error: ${uiState.msg}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    ListFragmentUiState.Loading -> {
                        mBinding.progressBar.visibility = View.VISIBLE
                    }

                    is ListFragmentUiState.Success -> {
                        mBinding.progressBar.visibility = View.GONE
                        mAdapter.refreshData(uiState.listPokemon)
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        mAdapter = PokemonAdapter(arrayListOf(), this, requireContext())
        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.recyclerView.adapter = mAdapter
    }

    override fun onPokemonClick(idPokemon: Int) {
        findNavController()
            .navigate(
                ListFragmentDirections
                    .actionListFragmentToDetailFragment(idPokemon)
            )
    }

    private fun setupScrollListener() {
        mBinding.recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = mBinding.recyclerView.layoutManager
                    val visibleItemCount = layoutManager?.childCount ?: 0
                    val totalItemCount = layoutManager?.itemCount ?: 0
                    val firstVisibleItemPosition =
                        (layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition() ?: 0

                    if (firstVisibleItemPosition + visibleItemCount >= totalItemCount) {
                        mViewModel.addPokemonToList()
                    }
                }
            })
    }
}