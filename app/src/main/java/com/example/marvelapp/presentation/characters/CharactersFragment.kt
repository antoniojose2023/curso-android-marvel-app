package com.example.marvelapp.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.marvelapp.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Suppress("SpacingAroundOperators", "MaximumLineLength", "SpacingAroundCurly", "MaxLineLength")
@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    val binding: FragmentCharactersBinding get() = _binding!!
    private lateinit var characterAdapter: CharacterAdapter
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
        initRecyclerViewCharacter()
        observableInitialLoadState()

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                characterViewModel.charactersData("").collect { pagingData ->
                    characterAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun initRecyclerViewCharacter() {
        characterAdapter = CharacterAdapter()
        with(binding.rvCharacter) {
            setHasFixedSize(true)
            scrollToPosition(0)
            adapter = characterAdapter.withLoadStateFooter(
                    footer = CharacterLoadMoreAdapter(
                    characterAdapter::retry
                )
            )
        }
    }

    private fun observableInitialLoadState() {
        lifecycleScope.launch {
            characterAdapter.loadStateFlow.collectLatest { loadState ->
              binding.flipperCharacters.displayedChild = when (loadState.refresh) {
                   is LoadState.NotLoading -> {
                      initShimmerLayout(false)
                      FLIPPER_CHILD_CHARACTERS
                  }
                  is LoadState.Loading -> {
                      initShimmerLayout(true)
                      FLIPPER_CHILD_LOAD
                  }
                  is LoadState.Error -> {
                      initShimmerLayout(false)
                      binding.includeLayoutCharacterErro.buttonRetry.setOnClickListener {
                          characterAdapter.refresh()
                      }
                      FLIPPER_CHILD_ERRO
                  }
                  else -> FLIPPER_CHILD_CHARACTERS
              }
            }
        }
    }

    private fun initShimmerLayout(visibility: Boolean) {
            binding.includeShimmerLayoutCharacter.containerShimmer.run {
                    this.isVisible = visibility
                    if (visibility) startShimmer() else stopShimmer()
            }
    }

    companion object {
        const val FLIPPER_CHILD_LOAD = 0
        const val FLIPPER_CHILD_CHARACTERS = 1
        const val FLIPPER_CHILD_ERRO = 2
    }
}
