package com.example.marvelapp.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.marvelapp.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@Suppress("SpacingAroundOperators", "MaximumLineLength", "SpacingAroundCurly", "MaxLineLength")
@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    val binding: FragmentCharactersBinding get() = _binding!!
    private val characterAdapter = CharacterAdapter()
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharactersBinding.inflate(inflater, container, false).apply {
         _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharacterAdapter()

        lifecycleScope.launch {
            characterViewModel.charactersData("").collect { pagingData ->
                 characterAdapter.submitData(pagingData)
            }
        }
    }

    private fun initCharacterAdapter() {
        with(binding.rvCharacter) {
            setHasFixedSize(true)
            adapter = characterAdapter
        }
    }
}
