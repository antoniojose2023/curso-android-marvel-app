package com.example.marvelapp.presentation.characters


import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import antoniojoseuchoa.com.core.domain.model.Character
import javax.inject.Inject

class CharacterAdapter @Inject constructor() : PagingDataAdapter<Character, CharacterViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
          return CharacterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }


    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                 return oldItem == newItem
            }
        }
    }
}
