package com.example.retrofitpokemon.ui.choice_generation_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.retrofitpokemon.databinding.FragmentChoiceGenerationBinding

class ChoiceGenerationFragment : Fragment() {

    private lateinit var mBinding: FragmentChoiceGenerationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentChoiceGenerationBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}