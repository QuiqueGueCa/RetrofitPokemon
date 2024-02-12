package com.example.retrofitpokemon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.retrofitpokemon.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var mBinding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentDetailBinding.inflate(inflater, container, false)

        return mBinding.root
    }
}