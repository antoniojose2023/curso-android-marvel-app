package com.example.marvelapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.ItemCharacterLoadMoreStateBinding

class CharacterLoadMoreViewHolder(
       itemBinding: ItemCharacterLoadMoreStateBinding,
       retry: () -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    val binding = ItemCharacterLoadMoreStateBinding.bind(itemView)
    val progressBarLoad = binding.progressbarLoadMore
    val tvMensageErro = binding.tvErroLoadMore.setOnClickListener {
         retry()
    }

    fun bind(loadState: LoadState) {
        progressBarLoad.isVisible = loadState is LoadState.Loading
        binding.tvErroLoadMore.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): CharacterLoadMoreViewHolder {
            val itemBinding = ItemCharacterLoadMoreStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
            return CharacterLoadMoreViewHolder(itemBinding, retry)
        }
    }
}
