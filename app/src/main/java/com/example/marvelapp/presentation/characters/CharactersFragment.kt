package com.example.marvelapp.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import antoniojoseuchoa.com.core.domain.model.Character
import com.example.marvelapp.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    val binding: FragmentCharactersBinding get() = _binding!!
    private val adapter = CharacterAdapter()

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

        adapter.submitList(
            listOf(
                Character("Spider Man", "https://tinyurl.com/2zmuz79v"),
                Character("Spider Man 2", "https://tinyurl.com/2zmuz79v"),
                Character("Spider Man 3", "https://tinyurl.com/2zmuz79v"),
            )
        )
    }

    private fun initCharacterAdapter() {
        binding.rvCharacter.run {
            setHasFixedSize(true)
            adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
