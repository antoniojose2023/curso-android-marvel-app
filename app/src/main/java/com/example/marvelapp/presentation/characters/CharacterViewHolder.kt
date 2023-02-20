package com.example.marvelapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import antoniojoseuchoa.com.core.domain.model.Character
import com.bumptech.glide.Glide
import com.example.marvelapp.databinding.ItemCharacterBinding

class CharacterViewHolder(binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    private val tvNameCharacter = binding.tvNameCharacter
    private val ivCharacter = binding.ivCharacter

    fun bind(character: Character) {
        tvNameCharacter.text = character.name
        Glide.with(itemView).load(character.image).into(ivCharacter)
    }

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
               val inflate = LayoutInflater.from(parent.context)
               val binding = ItemCharacterBinding.inflate(inflate, parent, false)
               return CharacterViewHolder(binding)
        }
    }
}
