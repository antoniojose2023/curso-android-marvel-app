package com.example.marvelapp.presentation.characters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class CharacterLoadMoreAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CharacterLoadMoreViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharacterLoadMoreViewHolder {
        return CharacterLoadMoreViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(holder: CharacterLoadMoreViewHolder, loadState: LoadState) {
            holder.bind(loadState)
    }
}
