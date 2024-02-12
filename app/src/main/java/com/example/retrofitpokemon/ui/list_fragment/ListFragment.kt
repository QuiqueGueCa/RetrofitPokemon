package com.example.retrofitpokemon.ui.list_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitpokemon.data.domain.usecase.GetListPokemonUseCase
import com.example.retrofitpokemon.databinding.FragmentListBinding
import com.example.retrofitpokemon.injection.InjectionSingleton
import com.example.retrofitpokemon.ui.adapter.PokemonAdapter
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private lateinit var mBinding: FragmentListBinding
    private lateinit var mAdapter: PokemonAdapter
    private val mViewModel: ListFragmentViewModel =
        ListFragmentViewModel(GetListPokemonUseCase(InjectionSingleton.provideDataSource()))

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
        mViewModel.getListPokemon()
    }

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
        val listLayout = LinearLayoutManager(requireContext())

        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.layoutManager = listLayout
        mBinding.recyclerView.adapter = mAdapter
    }
}